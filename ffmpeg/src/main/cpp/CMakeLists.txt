# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(jnilibs ${CMAKE_SOURCE_DIR}/../jniLibs)

# Creates and names a library, sets it as either STATIC

# or SHARED, and provides the relative paths to its source code.

# You can define multiple libraries, and CMake builds them for you.

# Gradle automatically packages shared libraries with your APK.
link_directories(${jnilibs}/${ANDROID_ABI})
set(third-party-libs avformat avcodec avfilter swresample swscale avutil)

include_directories(include ${CMAKE_SOURCE_DIR}/util)
file(GLOB src-files ${CMAKE_SOURCE_DIR}/*.cpp)
set(native-libs android EGL GLESv3 OpenSLES log m z)

add_library(

        #你可以自定义的动态库名字

        native-lib

        # Sets the library as a shared library.

        SHARED

        #你写好的cpp文件
        ${src-files}
)

# Searches for a specified prebuilt library and stores the path as a

# variable. Because CMake includes system libraries in the search path by

# default, you only need to specify the name of the public NDK library

# you want to add. CMake verifies that the library exists before

# completing its build.

find_library( # Sets the name of the path variable.

        log-lib

        # Specifies the name of the NDK library that

        # you want CMake to locate.

        log )

# Specifies libraries CMake should link to your target library. You

# can link multiple libraries, such as libraries you define in this

# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.

        #你想添加的动态库名字

        native-lib

        # Links the target library to the log library

        # included in the NDK.
        ${native-libs}
        ${log-lib}
        ${third-party-libs})
