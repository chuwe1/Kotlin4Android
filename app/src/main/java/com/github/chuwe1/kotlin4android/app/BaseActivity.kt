package com.github.chuwe1.kotlin4android.app

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView {

    var mCompositeSubscription: CompositeSubscription? = null
    var mLoadingDialog: ProgressDialog? = null

    var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = newPresenter()
    }

    /**
     * 子类重写此方法初始化Presenter
     */
    abstract fun newPresenter(): P

    override fun onDestroy() {
        hideLoadingDialog()
        if (mCompositeSubscription != null && mCompositeSubscription!!.hasSubscriptions()) {
            mCompositeSubscription?.unsubscribe()
            mCompositeSubscription?.clear()
        }
        super.onDestroy()
    }

    fun addSubscription(subscription: Subscription?) {
        if (subscription == null) return
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeSubscription()
        }
        mCompositeSubscription!!.add(subscription)
    }

    fun removeSubscription(subscription: Subscription?) {
        if (subscription == null || mCompositeSubscription == null) return
        if (!subscription.isUnsubscribed) subscription.unsubscribe()
        mCompositeSubscription!!.remove(subscription)
    }

    override fun showLoadingDialog(title: String?, message: String?, progressStyle: Int) {
        if (mLoadingDialog == null) {
            mLoadingDialog = ProgressDialog(this)
            mLoadingDialog?.setCancelable(true)
            mLoadingDialog?.setCanceledOnTouchOutside(false)
            mLoadingDialog?.setOnCancelListener { onLoadingDialogCancel() }
        }
        mLoadingDialog?.setTitle(title)
        mLoadingDialog?.setMessage(message)
        mLoadingDialog?.setProgressStyle(progressStyle)
        if (!mLoadingDialog!!.isShowing) {
            mLoadingDialog?.show()
        }
    }

    override fun hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog!!.isShowing) {
            mLoadingDialog?.dismiss()
        }
    }

    open fun onLoadingDialogCancel() {

    }
}