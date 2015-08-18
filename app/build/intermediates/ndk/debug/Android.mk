LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := modplug-1.0
LOCAL_CFLAGS := -I/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main
LOCAL_SRC_FILES := \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_ptm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_med.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_umx.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_mdl.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_xm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_it.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_ult.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_s3m.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_mod.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_far.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/fastmix.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_dmf.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_wav.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_mtm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/jni_stubs.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_mt2.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/snd_fx.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_j2b.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_mid.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_669.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_dbm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/snd_flt.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/Android.mk \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_dsm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_amf.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_okt.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/modplug.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_pat.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_stm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_ams.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/mmcmp.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_psm.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/load_abc.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/sndfile.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/sndmix.cpp \
	/home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni/snd_dsp.cpp \

LOCAL_C_INCLUDES += /home/andrea/Vinna/Games/frozenbubble_studio/app/src/main/jni
LOCAL_C_INCLUDES += /home/andrea/Vinna/Games/frozenbubble_studio/app/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
