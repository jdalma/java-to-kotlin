
# 자바에서 코틀린으로 [Wiki](https://github.com/jdalma/java-to-kotlin/wiki)

## 무지 목록

- **값 의미론**
  - 객체 사이의 동등성이 객체의 정체성(=물리적 주소)에 의해 결정되지 않고 **객체 내의 필드 값에 의해 결정되는게 값 의미론**
- [`kotlinlang docs` 확장 프로그램](https://kotlinlang.org/docs/extensions.html)
- `@JvmStatic`, `@JvmOverloads`
  - `@JvmStatic` 자바 클래스로 변환될 때 스태틱 메소드를 만들어준다. 
- `inline fun require(value: Boolean, lazyMessage: () -> Any): Unit`
- **호출 연산자 `!!.` , `?.`**
  - 실전에서 `!!`을 사용하는 몇 안되는 경우가 바로 테스트다.
- **단일식 함수 구문의 장단점**
- **엘비스 연산자 `?:`**
- **`Standard.kt`** 내부 `inline fun` 분석
- **불변 객체를 쓰면 되돌리기나 다시하기 등도 쉽게 구현할 수 있다.**
- [ADT Types](https://www.geeksforgeeks.org/abstract-data-types/)
- **인스턴스 컨텍스트**
- **수신 객체 지정 람다**
- **[Aliasing Error](https://martinfowler.com/bliki/AliasingBug.html)**
- **[테스트로 인한 설계 손상](https://dhh.dk/2014/test-induced-design-damage.html)**
- `Arrays.sort()`와 `Collections.sort()`차이  
- `asSequence()`
- `@file:JvmName`
- [`kotlinlang` constructors](https://kotlinlang.org/docs/classes.html#constructors)
- `data class`는 `static` 함수를 포함할 수 없나?
  - `private`필드는 `getter`가 생성되지 않는다.
  - 필드에 대입만해도 코틀린 컴파일러가 해당 필드의 `setter`를 호출한다
- 필드 뒤에 `private set`키워드를 추가한다면 `getter`만 노출된다
- `by` 키워드
  - 생성자 인자로 받은 객체의 인터페이스 모든 메서드를 전달한다고 선언하는 것
- `runCatching...onFailure`와 `try...catch`의 차이점
- `@Throws`
  - 코틀린은 체크 예외를 제공하지 않지만, 이 애너테이션을 사용하여 바이트 코드로 생성되는 메서드 시그니처에 예외를 추가해서 자바와의 상호 운용할 수 있다.
- `Sequence`에는 상태가 들어있기 때문에 값이 아니지만??
- `Iterable<T>.zip`
- `Collection<*>.indices`


