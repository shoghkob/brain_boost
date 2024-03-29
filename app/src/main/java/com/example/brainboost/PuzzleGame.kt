package com.example.brainboost

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.jvm.Throws

class PuzzleGame : AppCompatActivity() {

    var mCurrentPhotoPath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_puzzle_game)

        val am = assets

        try {
            val files = am.list("img")
            val grid = findViewById<GridView>(R.id.grid)

            grid.adapter = ImageAdapter(this@PuzzleGame)
            grid.onItemClickListener = AdapterView
                    .OnItemClickListener { adapterView, view, i, l ->
                        val intent = Intent(applicationContext, PuzzleActivity::class.java)
                        intent.putExtra("assetName", files!![i % files.size])
                        startActivity(intent)
                    }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this@PuzzleGame, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }

    fun onImageCameraClicked(view: android.view.View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this@PuzzleGame, e.message, Toast.LENGTH_LONG).show()
            }
            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(
                        this@PuzzleGame,
                        applicationContext.packageName + ".fileprovider",
                        photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_IMAGE_GAPTURE)
            }
        }
    }
    fun onImageGalleryClicked(view: android.view.View) {
        if (ContextCompat.checkSelfPermission(
                this@PuzzleGame,android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this@PuzzleGame, arrayOf(
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    ), REQUEST_PERMISSION_READ_EXTERNAL_STORAGE
            )
        }
        else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        if (ContextCompat.checkSelfPermission(
                        this@PuzzleGame, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this@PuzzleGame, arrayOf(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE
            )
        } else {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmsss").format(Date())
            val imageFileName = "JPEG_" + timestamp + "_"
            val storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
            )
            val image = File.createTempFile(
                    imageFileName, ".jpg", storageDir
            )
            mCurrentPhotoPath = image.absolutePath
            return image
        }
        return null
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.size > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    onImageCameraClicked(View(this@PuzzleGame))
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_GAPTURE && resultCode == RESULT_OK){
            var intent = Intent(
                    this@PuzzleGame,PuzzleActivity::class.java
            )
            intent.putExtra("mCurrentPhotoPath",mCurrentPhotoPath)
            startActivity(intent)
        }
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            var uri = data!!.data
            intent.putExtra("mCurrentPhotoPath",uri)
            startActivity(intent)
        }
    }

    companion object {
        private const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 2
        private const val REQUEST_IMAGE_GAPTURE = 1

        const val REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 3
        const val REQUEST_IMAGE_GALLERY = 4
    }
}