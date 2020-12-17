package com.nytimesapp.presentation.ui.base

import android.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.nytimesapp.data.local.DataManager
import com.nytimesapp.errorhandling.ErrorEntity
import com.nytimesapp.R
import com.nytimesapp.utils.OpenForTesting
import kotlinx.android.synthetic.main.layout_screen_loading.*
import okhttp3.ResponseBody
import javax.inject.Inject

open class BaseFragment: Fragment(), BaseListener {

    @Inject
    lateinit var dataManager: DataManager

    /*override fun attachBaseContext(newBase: Context?) {
        val base = ContextWrapper.wrap(newBase, AppController.lang)
        super.attachBaseContext(base)
    }*/

    override fun startLoading(viewLoader: View) {
        if(viewLoader is SwipeRefreshLayout)
            (viewLoader as SwipeRefreshLayout).isRefreshing = true
        else
            (pbLoading as ProgressBar).visibility = View.VISIBLE
    }

    override fun stopLoading(viewLoader: View) {
        if(viewLoader is SwipeRefreshLayout)
            (viewLoader as SwipeRefreshLayout).isRefreshing = false
        else
            (pbLoading as ProgressBar).visibility = View.GONE
    }

    override fun onLoadDataFailure(errorEntity: ErrorEntity) {
        when (errorEntity) {
            is ErrorEntity.Business -> onBusinessError(errorEntity.error)
            is ErrorEntity.Network -> showSnackBar(getString(R.string.internet_connection_error))
            is ErrorEntity.UnAuthorized -> showSnackBar("UnAuthorized")
            is ErrorEntity.ServerError -> onServerError()
            is ErrorEntity.NotFound -> showSnackBar("NotFound")
            is ErrorEntity.UnKnown -> showSnackBar("UnKnown")
        }
    }

    override fun onBusinessError(responseBody: ResponseBody?) {

    }

    override fun onServerError() {
        showSnackBar(resources.getString(R.string.server_error))
    }

    override fun showSnackBar(msg: String) {
        Snackbar.make(requireActivity().findViewById(R.id.main_layout),
            msg, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
    }

    override fun showShortToast(msg: String) {
        val toast = Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showLongToast(msg: String) {
        val toast = Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun showAlertDialog(msg: String) {
        var alertDialog: AlertDialog? = null

        val builder1 = AlertDialog.Builder(requireContext())
        builder1.setMessage(msg)
        builder1.setCancelable(true)

        builder1.setPositiveButton(getString(R.string.ok)) { _, _ -> alertDialog?.dismiss() }

        alertDialog = builder1.create()
        alertDialog.show()
    }

    override fun onErrorField(editText: EditText?, msg: String) {
        editText!!.requestFocus()
        editText.error = msg
    }
}