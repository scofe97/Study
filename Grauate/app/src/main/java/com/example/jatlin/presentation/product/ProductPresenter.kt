package com.example.jatlin.presentation.product

import android.util.Log
import com.example.jatlin.DBKey
import com.example.jatlin.data.entity.ProductEntity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ProductPresenter(
    private val view: ProductContract.View
) : ProductContract.Presenter {

    companion object {
        const val TAG = "ProductPresenter"
    }

    override val scope: CoroutineScope = MainScope()
    private var productList = mutableListOf<ProductEntity>()
    private val productDB: DatabaseReference by lazy { Firebase.database.reference.child(DBKey.DB_PRODUCT) }


    override fun onViewCreated() {
        fetchData()

    }

    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val productModel = snapshot.getValue(ProductEntity::class.java)
            productModel ?: return

            productList.reverse()
            productList.add(productModel)
            productList.reverse()

            view.setAdapter(productList)
            listState()
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            val updateeModel = snapshot.getValue(ProductEntity::class.java)
            val tempList = mutableListOf<ProductEntity>()
            productList.forEach{
                if(it.createdAt == updateeModel?.createdAt){
                    tempList.add(updateeModel!!)
                }else{
                    tempList.add(it)
                }
            }
            productList = tempList
            view.setAdapter(productList)
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val deleteModel = snapshot.getValue(ProductEntity::class.java)
            val tempList = mutableListOf<ProductEntity>()
            productList.forEach{
                if(it.createdAt != deleteModel?.createdAt) tempList.add(it)
            }
            productList = tempList
            view.setAdapter(productList)
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }

    private fun fetchData() = scope.launch {
        try {
            view.showLog("fetchData 실행")
            view.showLoadingIndicator()
            productList.clear()
            productDB.addChildEventListener(listener)

        } catch (e: Exception) {
            e.printStackTrace()
            view.showLog("fetchData 에러")
        } finally {
            view.hideLoadingIndicator()
            view.showLog("fetchData 완료")
        }
    }

    private fun listState() {
        if (productList.isEmpty()) {
            view.showEmptyList()
        } else {
            view.showProductList()
        }
    }


    override fun onDestroyView() {
        productDB.removeEventListener(listener)
    }


}