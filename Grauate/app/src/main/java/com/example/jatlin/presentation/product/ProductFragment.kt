package com.example.jatlin.presentation.product

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.jatlin.R
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.databinding.FragmentProductBinding
import com.example.jatlin.extensions.toGone
import com.example.jatlin.extensions.toVisible
import com.example.jatlin.extensions.toast
import com.example.jatlin.presentation.product.viewpager.ViewPagerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.scope.ScopeFragment


class ProductFragment : ScopeFragment(), ProductContract.View {

    companion object {
        const val TAG = "ProductFragment"
    }

    private lateinit var binding: FragmentProductBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var productListAdapter: ProductAdapter

    override val presenter: ProductContract.Presenter by inject()


    // view 가 초기화중
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater)
        return binding.root
    }

    // 완전히 view 가 생성됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }


    // 로딩바 보임
    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    // 로딩바 사라짐
    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    // 메세지창 뛰움
    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    // 로그 디버깅
    override fun showLog(message: String) {
        Log.d(TAG,message)
    }

    // 상품리스트가 존재하므로 보여줌
    override fun showProductList() {
        binding.productRecyclerView.toVisible()
        binding.emptyResultTextView.toGone()
    }

    // 상품리스트가 존재하지 않으므로 없다는 문구 출력
    override fun showEmptyList() {
        binding.productRecyclerView.toGone()
        binding.emptyResultTextView.toVisible()
    }

    // 프래그먼트 이동
    override fun navigateCreateFragment() {
        findNavController().navigate(R.id.action_productFragment_to_productCreateFragment)
    }

    // 프래그먼트 이동
    override fun navigateDetailProduct( productItem : ProductEntity) {
        // val action = ProductCreateFragmentDirections.actionProductCreateFragmentToProductFragment(productItem)
        val bundle = bundleOf("productItem" to productItem)
        findNavController().navigate(R.id.action_productFragment_to_productDetailFragment, bundle)
    }

    // 어댑터 연결
    override fun setAdapter(productList: List<ProductEntity>) {
        productListAdapter = ProductAdapter(onItemClicked = { productItem ->
            if (auth.currentUser != null) {
                // 로그인을 한 상태
                navigateDetailProduct(productItem)
            } else {
                // 로그인을 안한 상태
                showDescription("로그인을 하고 시도하세요")
            }
        })
        productListAdapter.submitList(productList)
        binding.productRecyclerView.adapter = productListAdapter
    }

    // 초기 뷰 설정
    private fun initViews() {
        with(binding) {
            productCreateButton.setOnClickListener {
                if(auth.currentUser != null){
                    navigateCreateFragment()
                }else{
                    showDescription("로그인을 하고 시도하세요")
                }
            }
            productCreateButton.setColorFilter(ContextCompat.getColor(requireActivity(),R.color.white_100))


            viewPager.adapter = ViewPagerAdapter(getViewPageColorList(),getViewPageTitleList())
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }


    private fun getViewPageColorList() : ArrayList<Int>{
        return arrayListOf<Int>(R.color.viewpage_color1,R.color.viewpage_color2,R.color.viewpage_color3,R.color.viewpage_color4,)
    }
    private fun getViewPageTitleList() : ArrayList<String>{
        return arrayListOf<String>("공지사항 1","공지사항 2","공지사항 3","공지사항 4")
    }

}