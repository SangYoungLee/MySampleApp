package com.syapp.brightcovetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brightcove.player.model.DeliveryType
import com.brightcove.player.model.Video
import com.brightcove.player.view.BaseVideoView

class MainActivity : AppCompatActivity() {

    private lateinit var baseVideoView: BaseVideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        baseVideoView = findViewById(R.id.brightcove_video_view)
        val video = Video.createVideo("http://media.w3.org/2010/05/sintel/trailer.mp4", DeliveryType.MP4)
        baseVideoView.add(video)
        baseVideoView.analytics.account = "1760897681001"
        baseVideoView.start()
    }
}
