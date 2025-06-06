// Copyright 2014 The Chromium Authors
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.


// This file is autogenerated by
//     third_party/jni_zero/jni_generator.py
// For
//     org/webrtc/AudioTrack

#ifndef org_webrtc_AudioTrack_JNI
#define org_webrtc_AudioTrack_JNI

#include <jni.h>

#include "third_party/jni_zero/jni_export.h"
#include "webrtc/sdk/android/src/jni/jni_generator_helper.h"


// Step 1: Forward declarations.

JNI_ZERO_COMPONENT_BUILD_EXPORT extern const char kClassPath_org_webrtc_AudioTrack[];
const char kClassPath_org_webrtc_AudioTrack[] = "org/webrtc/AudioTrack";
// Leaking this jclass as we cannot use LazyInstance from some threads.
JNI_ZERO_COMPONENT_BUILD_EXPORT std::atomic<jclass> g_org_webrtc_AudioTrack_clazz(nullptr);
#ifndef org_webrtc_AudioTrack_clazz_defined
#define org_webrtc_AudioTrack_clazz_defined
inline jclass org_webrtc_AudioTrack_clazz(JNIEnv* env) {
  return jni_zero::LazyGetClass(env, kClassPath_org_webrtc_AudioTrack,
      &g_org_webrtc_AudioTrack_clazz);
}
#endif


// Step 2: Constants (optional).


// Step 3: Method stubs.
namespace webrtc {
namespace jni {

static void JNI_AudioTrack_SetVolume(JNIEnv* env, jlong track,
    jdouble volume);

JNI_BOUNDARY_EXPORT void Java_org_webrtc_AudioTrack_nativeSetVolume(
    JNIEnv* env,
    jclass jcaller,
    jlong track,
    jdouble volume) {
  return JNI_AudioTrack_SetVolume(env, track, volume);
}


}  // namespace jni
}  // namespace webrtc

#endif  // org_webrtc_AudioTrack_JNI
