package com.masaibar.exoplayerrotatedemo

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter


object SimpleExoPlayerFactory {

    fun create(
            context: Context,
            uri: Uri
    ): SimpleExoPlayer {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        val player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(context),
                trackSelector
        )

        val dataSourceFactory: DataSource.Factory = DataSource.Factory {
            AssetDataSource(context)
        }

        val extractorsFactory = DefaultExtractorsFactory()
        val mediaSource = ExtractorMediaSource(
                uri,
                dataSourceFactory,
                extractorsFactory,
                null,
                null
        )

        player.prepare(mediaSource)

        return player
    }

}