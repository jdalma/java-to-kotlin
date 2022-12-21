package travelator.mobile

import java.util.*

/**
 * @see UserPreferencesJava 해당 자바 파일은 가변이지만, 이 코틀린 파일은 불변이다
 * var 키워드를 사용하면 코틀린 컴파일러가 프로퍼티마다 비공개 필드, 게터, 세터를 생성한다
 * val 키워드를 사용하면    "         프로퍼티마다 비공개 필드, 게터를 생성한다
 */
data class UserPreferences (
    val greeting: String,
    val locale: Locale,
    val currency: Currency
)
