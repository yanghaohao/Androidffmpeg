package com.example.ffmpeg

/**
 *    @author : younghow
 *    @date   : 2021/12/23 18:43
 *    description:
 */
class Video {

    external fun video() : String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}