package com.github.chuwe1.kotlin4android.app

import android.app.ProgressDialog
import com.github.chuwe1.kotlin4android.model.User

/**
 * 数据类基类
 */
open class BaseModel {

}

/**
 * View（Activity，Fragment实现此接口）基类
 */
interface BaseView {
    /*
     * 此处可定义一些公共的UI函数
     */

    fun showLoadingDialog(title: String? = "Loading",
                          message: String? = null,
                          progressStyle: Int = ProgressDialog.STYLE_SPINNER)

    fun hideLoadingDialog()
}

/**
 * Presenter（Activity，Fragment持有此类实例）基类
 */
abstract class BasePresenter(val view: BaseView) {
    /*
     * 此处可定义一些公共的逻辑（例如获取保存在数据库中的当前登录用户的UserInfo）
     */
    fun getLoginUserInfoFromDB(): User {
        TODO("Do what you want")
    }
}
