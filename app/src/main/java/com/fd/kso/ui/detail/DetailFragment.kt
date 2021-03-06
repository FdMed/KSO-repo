package com.fd.kso.ui.detail

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fd.kso.MyApplication
import com.fd.kso.R
import com.fd.kso.data.model.MyItem
import com.fd.kso.databinding.FragmentDetailBinding
import com.fd.kso.utils.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class DetailFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private var _binding: FragmentDetailBinding? = null
    var myItem: MyItem? = null

    private val REQUEST_LOCATION_PERMISSION = 1
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        myItem = arguments?.getParcelable<MyItem>(Utils.ITEM_BUNDLE_ARG)
        val mapFragment = childFragmentManager.findFragmentById(R.id.myMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        binding.departLocation.text = myItem?.departure?.place
        binding.departLocationInfos.text =
                getString(R.string.departure_value, myItem?.departure?.getFormattedDate(), myItem?.departure?.getFormattedTime())
        binding.arrivalLocation.text = myItem?.arrival?.place
        binding.arrivalLocationInfos.text =
                getString(R.string.departure_value, myItem?.arrival?.getFormattedDate(), myItem?.arrival?.getFormattedTime())

        if (myItem?.details?.duration_second != null)
            binding.durationValue.text = getString(R.string.duration_inline, myItem?.details?.getFormattedDuration())
        else binding.imageError.visibility = View.VISIBLE

        if (myItem?.details?.distance_m != null)
            binding.distanceValue.text = myItem?.details?.getDistanceKM()
        else binding.imageError2.visibility = View.VISIBLE

        binding.co2Value.text = myItem?.details?.co2_emission.toString()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.setOnMyLocationButtonClickListener(this)
        enableLocation()
    }

    private fun setupMarkers() {

        myItem?.run {

            Utils.myIfLet(departure.coord?.lat,
                departure.coord?.lon,
                arrival.coord?.lat,
                arrival.coord?.lon
            ) { (depLat, depLon, arrLat, arrLon) ->

                val departureLatLng = LatLng(depLat, depLon)
                val arrivalLatLng = LatLng(arrLat, arrLon)

                mMap.run {

                    addMarker(MarkerOptions()
                        .position(departureLatLng)
                        .title(getString(R.string.departure_title))
                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_departure_marker)))

                    addMarker(MarkerOptions()
                        .position(arrivalLatLng)
                        .title(getString(R.string.arrival_title))
                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_arrival_marker)))
                }

                val builder: LatLngBounds.Builder = LatLngBounds.Builder()
                builder.include(departureLatLng)
                builder.include(arrivalLatLng)

                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    val bounds = builder.build()
                    val width = resources.displayMetrics.widthPixels
                    val height = resources.displayMetrics.heightPixels / 2
                    val padding = (width * 0.20).toInt()
                    mMap.setOnMapLoadedCallback { mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)) }
                } else {
                    val bounds = builder.build()
                    val width = resources.displayMetrics.widthPixels / 2
                    val height = resources.displayMetrics.heightPixels
                    val padding = (width * 0.20).toInt()
                    mMap.setOnMapLoadedCallback { mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)) }
                }

            }
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun enableLocation() {

        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
            setupMarkers()
        } else {
            requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_PERMISSION
            )
            setupMarkers()
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
    ) {

        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
            enableLocation()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

}

