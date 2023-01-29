package com.example.ch3_5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ch3_5.DBkey.Companion.DIS_LIKE
import com.example.ch3_5.DBkey.Companion.LIKED_BY
import com.example.ch3_5.DBkey.Companion.LIKE
import com.example.ch3_5.DBkey.Companion.NAME
import com.example.ch3_5.DBkey.Companion.USERS
import com.example.ch3_5.DBkey.Companion.USER_ID
import com.example.ch3_5.databinding.ActivityLikeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class LikeActivity : AppCompatActivity(), CardStackListener {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var userDB: DatabaseReference

    private val adapter = CardItemAdapter()
    private val cardItems = mutableListOf<CardItem>()
    private val manager by lazy{
        CardStackLayoutManager(this,this)
    }

    private lateinit var binding: ActivityLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCardStackView()
        initSignOutButton()
        initMatchedListButton()

        userDB = Firebase.database.reference.child(USERS)
        val currentUserDB = userDB.child(getCurrentUserId())

        // db 에서 값을 가져오는 방법 -> 리스너를 달아줌
        // 하지만 싱글이벤트만 사용하므로 밑의 메소드를 사용
        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // 데이터가 변함, 최초에도 발생

                // snapshot -> 우리유저 정보
                // 최초에는 userId 밖에없음
                if (snapshot.child(NAME).value == null) {
                    showNameInputPopup()
                    return
                }
                // 유저정보를 갱신
                getUnSelectedUsers()
            }

            override fun onCancelled(error: DatabaseError) {
                // 취소가 발생함
            }
        })
    }

    private fun initSignOutButton(){
        val signOutButton = binding.signOutButton
        signOutButton.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun initMatchedListButton(){
        val matchedListButton = binding.matchListButton
        matchedListButton.setOnClickListener {
            startActivity(Intent(this, MatchedUserActivity::class.java))
        }
    }

    private fun initCardStackView() {
        val stackView = binding.cardStackView

        stackView.layoutManager = manager
        stackView.adapter = adapter
    }

    private fun getUnSelectedUsers() {
        userDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // 데이터가 추가됨 (초기에 데이터 불러옴, 화면을 불러오는데 새로운 유저가 추가됨 )

                if (snapshot.child(USER_ID).value != getCurrentUserId()
                    && snapshot.child(LIKED_BY).child(LIKE).hasChild(getCurrentUserId()).not()
                    && snapshot.child(LIKED_BY).child(DIS_LIKE).hasChild(getCurrentUserId()).not()
                ) {
                    // 1. 유저 ID가 내 ID와 다르다 ( 나의 정보가 아니다 )
                    // 2. 내가 해당유저에게 좋아요를 주지않았다
                    // 3. 내가 해당유저에게 싫어요를 주지않았다.

                    val userId = snapshot.child(USER_ID).value.toString()
                    var name = "undecided"
                    if (snapshot.child(NAME).value != null) {
                        name = snapshot.child(NAME).value.toString()
                    }

                    cardItems.add(CardItem(userId, name))
                    adapter.submitList(cardItems)
                    // 리사이클러뷰 갱신
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // 데이터가 변경됨 (이름이 바뀌거나, 다른 유저가 like 하는 경우)

                cardItems.find { it.userId == snapshot.key }?.let {
                    it.name = snapshot.child(NAME).value.toString()
                }
                adapter.submitList(cardItems)
                adapter.notifyDataSetChanged()

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // 데이터가 제거됨
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // 데이터의 순서 바뀜
            }

            override fun onCancelled(error: DatabaseError) {
                // 데이터가 취소됨
            }

        })
    }


    private fun showNameInputPopup() {
        val editText = EditText(this)

        AlertDialog.Builder(this)
            .setTitle("이름을 입력해주세요")
            .setView(editText)
            .setPositiveButton("저장") { _, _ ->
                if (editText.text.isEmpty()) {
                    showNameInputPopup()
                } else {
                    saveUserName(editText.text.toString())
                }
            }
            // 다른 행동 불가
            .setCancelable(false)
            .show()
    }

    private fun saveUserName(name: String) {
        val userId = getCurrentUserId()
        val currentUserDB = userDB.child(userId)
        val user = mutableMapOf<String, Any>()
        user[USER_ID] = userId
        user[NAME] = name
        currentUserDB.updateChildren(user)

        // 유저정보를 가져와라
        getUnSelectedUsers()
    }

    private fun getCurrentUserId(): String {
        if (auth.currentUser == null) {
            Toast.makeText(this, "로그인이 되어있지 않습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        return auth.currentUser?.uid.orEmpty()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    private fun like() {
        val card = cardItems[manager.topPosition - 1]
        cardItems.removeFirst()

        userDB.child(card.userId)
            .child(LIKED_BY)
            .child(LIKE)
            .child(getCurrentUserId())
            .setValue(true)

        saveMatchIfOtherUserLikeMe(card.userId)

        Toast.makeText(this,"${card.name}님을 Like 하셨습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun disLike() {
        val card = cardItems[manager.topPosition - 1]
        cardItems.removeFirst()

        userDB.child(card.userId)
            .child("likedBy")
            .child("disLike")
            .child(getCurrentUserId())
            .setValue(true)

        // 매칭이 된 시점점
        Toast.makeText(this,"${card.name}님을 disLike 하셨습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun saveMatchIfOtherUserLikeMe(otherUserId : String){
        val otherUserDB = userDB.child(getCurrentUserId()).child("likedBy").child("like").child(otherUserId)
        // 한번만 데이터 값을 알아보는 메서드
        otherUserDB.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value == true){
                    userDB.child(getCurrentUserId())
                        .child("likedBy")
                        .child("match")
                        .child(otherUserId)
                        .setValue(true)

                    userDB.child(otherUserId)
                        .child("likedBy")
                        .child("match")
                        .child(getCurrentUserId())
                        .setValue(true)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

    override fun onCardSwiped(direction: Direction?) {
        // 우측 Like , 좌측 DisLike

        when (direction) {
            Direction.Right -> {
                like()
            }
            Direction.Left -> {
                disLike()
            }
            else -> {
            }
        }
    }

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}

}