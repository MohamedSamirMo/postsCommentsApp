package com.example.postsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.postsapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
//    lateinit var binding:ActivityMainBinding
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
}
    val adapter: PostsAdapter by lazy {
        PostsAdapter()
    }
    val CompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.onItemClickListener = object : PostsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Intent(this@MainActivity, CommentsActivity::class.java).apply {
                    putExtra("postId", position)
                    startActivity(this)
                }
            }

        }
        ConnectRetrofit.apiServices.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<PostModelX>> {
                override fun onSubscribe(d: Disposable) {
                    CompositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {

                }

                override fun onSuccess(t: List<PostModelX>) {
                    adapter.posts = t as ArrayList<PostModelX>
                    binding.recposts.adapter = adapter
                }

            })

    }
    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable.clear()
    }
}
