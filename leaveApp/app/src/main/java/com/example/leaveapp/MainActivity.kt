package com.example.leaveapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.ImageDecoder.Source
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leaveapp.ml.Leaf
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.lang.Float.max
import java.nio.ByteBuffer
import java.util.Collections.max

class MainActivity : AppCompatActivity() {

     lateinit var bitmap: Bitmap
    lateinit var imgview: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgview = findViewById(R.id.imageView)
        val fileName = "dict.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use {it.readText()}
        var townList = inputString.split("\n")
        var tv:TextView = findViewById(R.id.textView)

        var select: Button = findViewById(R.id.button);
        select.setOnClickListener(View.OnClickListener {

            var intent:Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent,100)


        })


        //predict button setonclicklistener(functionality)
        var predict:Button = findViewById(R.id.button2)
        predict.setOnClickListener(View.OnClickListener {

            tv.setText(" ")



            var resized:Bitmap = Bitmap.createScaledBitmap( bitmap, 224,224, true )
            val model = Leaf.newInstance(this)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3 ) , DataType.FLOAT32)

            var tbuffer = TensorImage.fromBitmap(resized)
            var byteBuffer = tbuffer.buffer
            //byteBuffer.remaining()
            //byteBuffer = ByteBuffer.allocate(451584)
            var a = byteBuffer.remaining()

            byteBuffer = ByteBuffer.allocateDirect(224*224*3*4)




            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
            //var m= outputFeature0.floatArray.max()



            var max = getMax(outputFeature0.floatArray)

            //tv.setText(outputFeature0.floatArray[13].toString())
            tv.setText(outputFeature0.floatArray[2].toString())


            //tv.setText(max.toString())


            model.close()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imgview.setImageURI(data?.data)

        var uri: Uri ? =data?.data

        val source: Source = ImageDecoder.createSource(this.contentResolver , uri!!)
        this.bitmap = ImageDecoder.decodeBitmap(source)
        bitmap = ARGBBitmap(bitmap)
        //bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
    }


    fun getMax(arr:FloatArray) : Float {



        var ind = 0
        var min = 0.0f

        for(i in 0..2)
            if(arr[i]>min)
            {
                ind = i
                min = arr[i]
            }

        return  min
    }


    private fun ARGBBitmap(img: Bitmap): Bitmap {
        return img.copy(Bitmap.Config.ARGB_8888, true)
    }



}