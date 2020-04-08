package com.syapp.todo.util

import android.os.Build
import androidx.fragment.app.FragmentManager
import io.reactivex.rxjava3.core.Observable
import java.lang.ref.WeakReference

/**
 * Created By lsy2014 on 08/04/2020
 */
class PermissionManager(private val fragmentManager: FragmentManager) {

    companion object {
        private const val TAG = "PermissionManager"
    }

    private var permissionFragmentRefer: WeakReference<PermissionFragment>? = null

    private fun initPermissionFragment() {
        (fragmentManager.findFragmentByTag(TAG) as? PermissionFragment)?.let {
            permissionFragmentRefer = WeakReference(it)
        } ?: run {
            val permissionFragment = PermissionFragment().also {
                permissionFragmentRefer = WeakReference(it)
            }

            fragmentManager.beginTransaction()
                .add(permissionFragment, TAG)
                .commitNowAllowingStateLoss()
        }
    }

    fun request(vararg permissions: String): Observable<Permission> {
        if (permissions.isEmpty()) {
            throw IllegalStateException("Permissions is empty")
        }

        if (!isMarshmallow()) {
            return Observable.just(Permission(permissions.joinToString(","),
                isGranted = true,
                shouldShowRequestPermissionRationale = false
            ))
        }

        if (permissionFragmentRefer?.get() == null) {
            initPermissionFragment()
        }

        return Observable.fromIterable(listOf(permissions))
            .flatMap { permissionFragmentRefer?.get()!!.requestPermissions(it) }
            .reduce { sumPermissions, otherPermission ->
                sumPermissions.apply {
                    this.permissionName += ", ${otherPermission.permissionName}"
                    this.isGranted = (this.isGranted && otherPermission.isGranted)
                    this.shouldShowRequestPermissionRationale = (this.shouldShowRequestPermissionRationale && otherPermission.shouldShowRequestPermissionRationale)
                }
            }.toObservable()
    }

    private fun isMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT >= 23
    }
}

data class Permission(
    var permissionName: String,
    var isGranted: Boolean,
    var shouldShowRequestPermissionRationale: Boolean
)