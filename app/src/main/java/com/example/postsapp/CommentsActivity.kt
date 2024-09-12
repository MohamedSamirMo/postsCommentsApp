package com.example.postsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.postsapp.databinding.ActivityCommentsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CommentsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityCommentsBinding.inflate(layoutInflater)
    }
    val adapter: CommentAdapter by lazy {
        CommentAdapter()
    }
    val CompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ConnectRetrofit.apiServices.getComments(
            intent.getIntExtra("postId", 0)
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<CommentsModel>>{
                override fun onSubscribe(d: Disposable) {
                    CompositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onSuccess(t: List<CommentsModel>) {
                    adapter.Comments = t as ArrayList<CommentsModel>
                    binding.recComments.adapter = adapter
                }

            })
    }
}