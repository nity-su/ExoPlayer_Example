package com.anlyn.exoplayer_example

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ExoPlayerAttr(context:Context) {
    val exoPlayer:ExoPlayer
    init {
        //default classes 일반적인 경우라면 디폴트 값이 쓰임

        //각각 플레이 구간 제어
        val trackSelector = DefaultTrackSelector()
        //미디어 버퍼링 제어
        val loadControl = DefaultLoadControl()
        //ExoPlayer 사용할 수 있도록 렌더링
        val renderersFactory = DefaultRenderersFactory(context)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(context,renderersFactory,trackSelector,loadControl)
        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        /*
        extractor 컨테이너 포맷을 추출한다.
        DefaultDataSourceFactory 대부분의 케이스에 적용 가능
        그외에는 DataSource를 상속하는 클래스(혹은 인터페이스)를 따르도록 하자
        */
        val mediasource = ExtractorMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent,null))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse("url"))
        exoPlayer.prepare(mediasource)
    }

    fun releasePlayer() {
        exoPlayer.stop()
        exoPlayer.release()
    }
}