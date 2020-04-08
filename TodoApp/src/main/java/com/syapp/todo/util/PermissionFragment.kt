package com.syapp.todo.util

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * 퍼미션 요청을 위한 Empty Fragment
 */
class PermissionFragment : Fragment() {

    companion object {
        private const val RQ_PERMISSION_CODE = 54321
    }

    private var permissionSubject = PublishSubject.create<Permission>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != RQ_PERMISSION_CODE) return

        permissions.forEachIndexed { index, permission ->
            permissionSubject.onNext(
                Permission(permission,
                    grantResults[index] == PackageManager.PERMISSION_GRANTED,
                    shouldShowRequestPermissionRationale(permission)))
        }

        permissionSubject.onComplete()
    }

    fun requestPermissions(permissionList: Array<out String>): Observable<Permission> {
        permissionSubject = createPermissionSubject()
        requestPermissions(permissionList, RQ_PERMISSION_CODE)
        return permissionSubject
    }

    private fun createPermissionSubject() = PublishSubject.create<Permission>()
}