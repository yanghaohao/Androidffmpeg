#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_zgw_company_aibuildingthree_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    
    return env->NewStringUTF(hello.c_str());
}extern "C"

JNIEXPORT jstring JNICALL
Java_com_example_ffmpeg_Video_video(JNIEnv *env, jobject thiz) {

}