#include <jni.h>
#include <stdio.h>

JNIEXPORT jint
JNICALL
Java_id_ac_ui_cs_mobileprogramming_lutfiarif_tesjuz_view_fragments_QuizResultFragment_getFinalScore(JNIEnv
* env,
jobject thiz, jint
wrong_answer) {
    jint result;
    result = 100 - wrong_answer;
    return result;
}
