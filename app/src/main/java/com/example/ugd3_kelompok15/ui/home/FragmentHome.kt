package com.example.ugd3_kelompok15.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ugd3_kelompok15.HomeActivity
import com.example.ugd3_kelompok15.LoginActivity
import com.example.ugd3_kelompok15.R
import com.example.ugd3_kelompok15.qrcode.QrCodeActivity
import com.example.ugd3_kelompok15.room.UserDB
import com.example.ugd3_kelompok15.ui.janjitemu.JanjiTemuActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.system.exitProcess

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val db by lazy { UserDB(activity as HomeActivity) }
//        val userDao = db.userDao()
        val btnLogout : Button = view.findViewById(R.id.btnLogout)
        val btnJanjiTemu: Button = view.findViewById(R.id.btn_janji_temu)
        val btnImage: ImageView = view.findViewById(R.id.homeHospital)
        val textNama: TextView = view.findViewById(R.id.textHome)
        val textpic: TextView = view.findViewById(R.id.textpic)
        val qrCode: ImageView = view.findViewById(R.id.qrcode)

        val sharedPreferences = (activity as HomeActivity).getSharedPreferences()
     //   val user = userDao.getUser(sharedPreferences.getInt("id", 0))
        textNama.setText("Hi, " + sharedPreferences.getString("nama",null))

        btnImage.setOnClickListener{
            val url = "https://picsum.photos/200"
            Glide.with(this)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(homeHospital)
            textpic.setText("")
        }

        qrCode.setOnClickListener {
            startActivity(Intent(this@FragmentHome.context, QrCodeActivity::class.java))
        }

        btnJanjiTemu.setOnClickListener(View.OnClickListener {
           val movejanji = Intent(this@FragmentHome.context, JanjiTemuActivity::class.java)
            startActivity(movejanji)

        })

        btnLogout.setOnClickListener(View.OnClickListener {
            getActivity()?.let { it1 ->
                AlertDialog.Builder(it1).apply {
                    setTitle("Tolong Konfirmasi")
                    setMessage("Apakah anda yakin ingin keluar?")

                    setPositiveButton("Iya") { _, _ ->
                        val intent = Intent(this@FragmentHome.context, LoginActivity::class.java)
                        intent.putExtra("finish", true)
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }

                    setNegativeButton("Tidak"){_, _ ->

                    }
                    setCancelable(true)
                }.create().show()
            }
        })
    }
}