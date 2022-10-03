package com.example.ionixtest.presentation.features.spalsh

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ionixtest.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
            }
        }
    }

    // Function to check and request permission.
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(requireActivity(), permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            requestPermissions(arrayOf(permission), requestCode)
        } else {
            lifecycleScope.launch {
                delay(2000)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }
}