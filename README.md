
```
"리팩터링을 하고 싶다면, 필수 전제조건은 탄탄한 테스트가 있어야 한다." - 마틴 파울러
```

## 무지 목록

- **값 의미론**
  - 객체 사이의 동등성이 객체의 정체성(=물리적 주소)에 의해 결정되지 않고 **객체 내의 필드 값에 의해 결정되는게 값 의미론**
- [`kotlinlang docs` 확장 프로그램](https://kotlinlang.org/docs/extensions.html)
- **동반 객체(Companion Object)**
- `@JvmStatic`, `@JvmOverloads`
  - `@JvmStatic` 자바 클래스로 변환될 때 스태틱 메소드를 만들어준다.
- `inline fun require(value: Boolean, lazyMessage: () -> Any): Unit`
- `Legs` 선언 시 `object` 키워드
- **함수 추출(Extract Function)**
- **호출 연산자 `!!.` , `?.`**
  - 실전에서 `!!`을 사용하는 몇 안되는 경우가 바로 테스트다.
- **단일식 함수 구문의 장단점**
- **엘비스 연산자 `?:`**
- **`Standard.kt`** 내부 `inline fun` 분석
- **불변 객체를 쓰면 되돌리기나 다시하기 등도 쉽게 구현할 수 있다.**
- [ADT Types](https://www.geeksforgeeks.org/abstract-data-types/)
- 코틀린의 다형성
- `kotlinc`
- **인스턴스 컨텍스트**

## 1주차 모임 `1장 ~ 4장`

- **코틀린은 값을 반환하거나 반환하지 않는다**는 말의 의미
- `equals()`를 오버리이딩 할 때 이미 `hashCode()`가 있으니 `hashCode()`를 사용하면 되지 않을까?
  - `this`와 `that`
- 코틀린 컴파일러가 자바로 변환할 때 `final class`로 해준다 
  - **자바 변환기를 사용할 때 `final class`를 인식하고 사용해라**
- `require()`를 사용하는 건 어떻게 생각하냐
  - 자바는 예외를 밖으로 밀어내는 컨셉이다
      - 사용당하는 함수는 자기 책임에만 맞는 내용에 집중한다
  - 코틀린은 모든 예외를 **런타임 예외**로 바꾼다
    - `null`을 예외 대신 쓰기도 한다
    - `try-catch`를 통해 예외를 풍부하게 알려주려고 하는 개발자도 있고 `null`로 처리하는 개발자도 있다.
- 자바는 확장을 가능하게 모두 열어두지만 **코틀린은 필요할 때만 확장하게 하려는 컨셉**
- **생성자와 팩토리 메소드가 `서로 같은 처리를 하지 않는다면` 생성자를 감춰야 한다**
- 코틀린에 있는 `substring()`은 인덱스가 범위를 벗어난다면 **런타임 예외**를 바로 던진다.
- `"코틀린에서는 최상위 상태와 함수 ..."` -> 코틀린에서는 클래스 밖에 선언이 가능하다
- 자바 클래스를 코틀린에서 쓸 때 컴파일러가 프로퍼티를 자바빈즈 규약에 맞는 `getXXX()`로 변환한다
- `data class`는 왜 캡슐화를 제공하지 않는가? -> `copy()`가 자동으로 제공되기 때문에 생성자를 거치지 않고 가능해서 그렇다
  - 필드 수 만큼 `component`도 만들어준다

```kotlin
data class A(val a:String, val b:String)
val (a, b) = A("a", "b")
```

- 코틀린의 `Iterator`와 `Iterable`은 자바의 호환성을 위해서만 존재한다
  - `Opreator.next()`와 `Opreator.hasNext()`로도 구분한다
- 리팩토링은 **시그니처를 변경하지 않고 내부 로직을 변경하는 것**
  - [ParallelChange](https://martinfowler.com/bliki/ParallelChange.html?fbclid=IwAR2fmOvTcYzV6n4J7xm1H3WKdZU4CV4oOKc7bDUseB4o-Cp1sodleMI5pGw)
- 코틀린으로는 컬렉션 가지고 제어문을 작성하려 하지마라 **유틸리티가 넘쳐난다.**


***

# **1장. 소개**

## 코틀린의 결

코틀린에서 `함수`와 `데이터`는 그 자체로 **독립적인 1급 기능**이며 클래스의 멤버로 선언될 필요가 없다.  
코틀린에서 가장 매력적인 기능은 **타입 시스템에서 널 가능성을 표현하는 능력**일 것이다.

- [왜 코틀린인가?](https://kotlinlang.org/#why-kotlin) 
   - 간결성
   - 안전성
   - 상호 운용성
   - 도구 친화성

1. **코틀린은 가변 상태를 변경하는 것보다 불변 데이터를 변환하는 쪽을 더 선호한다.**
   - 데이터 클래스를 사용하며 `값 의미론`을 제공하는 새로운 타입을 쉽게 정의할 수 있다.
2. **코틀린은 동작을 명시적으로 작성하는 쪽을 더 선호한다.**
   - 코틀린에는 암시적인 타입 변환이 없다.
   - 심지어 더 작은 데이터 타입을 더 큰 데이터 타입으로 자동으로 변환해 주지도 않는다.
     - 자바는 정보 손실이 없으므로 `int`를 `long`으로 암시적으로 변환해 주지만,
     - 코틀린에서는 `Int.toLong()`을 명시적으로 호출해야한다.
   - `명시성`을 선호하는 경향은 **흐름 제어에서 더 강하다.**
3. **코틀린은 동적 바인딩보다 정적 바인딩을 더 선호한다.**
   - 코틀린은 `타입 안전한`, `합성적인 코딩 스타일`을 장려한다.
   - **확장 함수**는 정적으로 바인딩된다. ❓
   - 기본적으로 클래스는 확장될 수 없고, 메서드는 다형적이지 않다.
   - **명시적으로 다형성과 상속을 활성화해야 한다.** 📌
4. **코틀린은 특별한 경우를 좋아하지 않는다.**
   - **원시 타입과 참조 타입 사이에 구분이 없다.**
   - 반환 시 아무 값도 돌려주지 않는 함수에 대한 `void`타입도 없다.
   - **확장 함수**를 사용하면 기존 타입에 새로운 연산을 추가할 수 있고,
     - 호출하는 쪽의 코드에서는 기존 연산과 확장 함수를 구분할 수 없다.
     - 인라인 함수를 사용해 새로운 제어 구조를 작성할 수도 있다.
5. **코틀린은 마이그레이션을 쉽게 하기 위해 자신의 규칙을 깬다.**
   - 코틀린 언어에는 숙어처럼 사용하는 자바와 코틀린 코드가 동시에 존재하도록 허용하기 위한 기능이 들어있다.
   - 어떤 프로퍼티를 `lateinit var`로 선언하면 이 값을 초기화할 책임은 개발자에게 있다.

## 코틀린으로 리팩토링하기

### [git bisect](https://git-scm.com/docs/git-bisect/)를 고려해 커밋한다.

커밋 이력이 아주 크고, 서로 무관한 변경 사항이 이리저리 섞여 있다면 `git bisect`가 큰 도움이 되지 않는다.  
따라서 각 리팩토링과 행동 변경을 서로 분리할 수 있도록 작게 커밋한다.  

***

# **2장. 자바 프로젝트에서 코틀린 프로젝트로**

## 전략

**자바의 한계**  
1. 불변 값 타입을 구현하기 위해 필요한 장황한 코드
2. 원시 타입과 참조 타입의 분리
3. 널 참조
4. 일반적인 고차 함수지원이 부족한 스트림
등등..

***

# **3장. 자바 클래스에서 코틀린 클래스로**

1. **생성자 파라미터를 한 줄에 하나씩 배치하기**
2. 코틀린 클래스 정의에는 `클래스 이름` , `주 생성자` , `상위 클래스` , `인터페이스`
   - 주 생성자안에는 파라미터와 프로퍼티 정의가 있을 수 있다.
   - 상위 클래스 뒤에 괄호를 붙여서 상위 클래스 생성자를 호출할 수도 있다.
   - 클래스 본문에서 프로퍼티와 생성자를 추가로 정의할 수 있다.
   - **동반 객체(Companion Object)** 도 본문에 들어간다.

## 데이터 클래스의 한계

**클래스 앞에 `data` (변경자)를 붙이면 컴파일러가 사용자가 정의하지 않은 `equals`, `hashCode`, `toString`, `copy` 메서드를 자동으로 대신 생성해 준다.**  
하지만 **데이터 클래스는 캡슐화를 제공하지 않는다.**  

`copy()`
- 데이터 클래스 객체의 모든 프로퍼티 값을 그대로 복사한 새 객체를 생성하되,
- 원하면 일부를 다른 값으로 대치할 수 있다.
- 유용한 메서드 이지만 **값의 내부 상태에 직접 접근하도록 허용해서 불변 조건을 깰 수 있다.**

비즈니스 로직에서 중간 결과를 저장하거나,  
데이터를 임시 구조에 담아서 애플리케이션 로직을 더 쉽게 작성할 수 있게 할 때 사용하긴 한다.  

하지만 값 타입이 불변 조건을 유지해야 하거나 내부 표현을 캡슐화해야 한다면 데이터 클래스가 적합하지 않다.  
이런 경우에는 **직접 값 의미론을 구현해야 한다.**  
**프로퍼티 사이에 불변 조건을 유지해야 하는 값 타입들은 데이터 클래스를 사용해 정의하지 말라**

## BigDecimal

`BigDecimal.setScale()`은 혼란을 일으킬 수 있다.  
세터처럼 보이지만 실제로 `BigDecimal`객체의 상태를 변경하지는 않는다.  
`setScale()`은 새로 설정한 정밀도에 맞춘 **새 BigDecimal을 반환**한다.  

요즘은 객체 상태를 변화시키지 않는 메서드에 `set`이라는 접두사를 붙이는 것을 피하는 대신 메서드가 객체를 변환한 새 객체를 돌려준다는 사실을 강조하는 이름을 사용한다.  
일반적인 관습은 프로퍼티 하나에만 영향을 끼치는 경우 `with`접두사를 사용하는 것이다.  

```java
// 기존
amount.setScale(currency.getDefaultFractionDigits());

// 아래와 같았으면 좋았겠지
amount.withScale(currency.getDefaultFractionDigits());
```

`BigDecimal`을 사용하는 코드를 아주 많이 사용해야 한다면, 아래와 같은 메서드를 추가할 만한 가치가 있다.  
```kotlin
fun BigDecimal.withScale(int scale, RoundingMode mode) = setScale(scale, mode)
```

***

# **4장. 옵셔널에서 널이 될 수 있는 타입으로**

코틀린은 **널을 포용**한다.  
함수형 프로그래머들은 코틀린의 널 가능성 대신 옵셔널 타입(또는 **메이비**타입)을 사용하라고 권장하지만,  
코틀린에서 `Optinal`을 쓰면 안되는 한 가지 이유는 **널 가능성을 지원하기 위해 구체적으로 설계된 언어 기능을 쓰지 못 하게 된다는 점**에 있다.  

코틀린 타입 시스템에서 `T`는 `T?`의 하위 타입이다.  
반대로 `T`는 `Optional<T>`의 하위 타입이 아니다.  
`Optional<String>`을 반환하는 함수가 있는데 반환 타입을 `String`으로 바꾼다면 모든 클라이언트 코드가 깨져버린다.  
**반환 타입이 `String?`이었다면 그냥 그 타입을 `String`으로 변경해도 호환성이 유지될 수 있다.**  

**널 가능성**을 사용하면 선택적인 값을 선택적이지 않은 값으로 쉽게 변경할 수 있지만,  
`Optional`을 사용하면 이런 변경이 쉽지 않다.

## 옵셔널에서 널 가능성으로 리팩토링 하기

코틀린 함수에서 널이 될 수 없는 파라미터를 지정하면 컴파일러가 함수 본문 이전에 널 검사를 추가해준다. **(컴파일 코드에 없는 것 같은데..)** 

```kotlin
@kotlin.jvm.JvmStatic 
public final fun findLongestLegOver(
    legs: kotlin.collections.List<travelator.Leg>, 
    duration: java.time.Duration
): java.util.Optional<travelator.Leg> { /* compiled code */ }
```

`Optional<Leg>`와 `Leg?`를 반환하는 함수를 비교해보자 [`LongestLegOverTests.kt` 예제 코드](https://github.com/jdalma/java-to-kotlin/commit/fa38dd02ee91179300935e9284e2d090022735a5)  
자바 클라이언트는 `Optional<Leg>`를 반환하는 `findLongestLegOver()`를 사용   
코틀린 클라이언트는 널이 될 수 있는 `Leg?`를 반환하는 `longestLegOver()`를 사용  



### **이터레이션과 `for`루프**

코틀린에서는 `Iterable`이 아닌 다른 타입을 `for`루프에 사용할 수도 있다.
- [`jdalma` Java Iterator, Enumerator, Iterable ?](https://jdalma.github.io/docs/lab/iterator_vs_enumeration/)

1. `Iterable`을 확장하는 타입
2. `Iterator`를 반환하는 `iterator()` 제공하는 타입
3. `Iterator`를 반환하는  `T.iterator()` 확장 함수가 영역 안에 정의된 `T 타입`

- 코틀린의 `Iterator`와 `Iterable`은 자바의 호환성을 위해서만 존재한다  
  - `Opreator.next()`와 `Opreator.hasNext()`로도 구분한다  

두 번째와 세 번째 방식은 해당 타입을 `Iterable`로 만들어 주지는 못하며, 코틀린 `for`루프만 적용할 수 있을 뿐이다.  

## 코틀린다운 코드로 리팩토링 하기 [예제 코드](https://github.com/jdalma/java-to-kotlin/commit/8000db5ab59dd98cd64b08c096c089a49662dde6)

코틀린에서는 추가적인 `네임스페이스`가 필요없다.    
`Move to top leve (최상위로 옮기기)`를 `longestLegOver`에 적용할 수 있다.  

***

# **5장. 가변 객체를 불변 데이터로**

객체는 **가변 상태를 관리하는 문제에 대한 해결책 이었다.**  

여기서 **값**이라고 말하면 **값 의미론**을 따르는 `구체적인 원시 타입`이나 `참조 타입`을 뜻한다.  
- 자바 원시 타입 값은 모두 **값 의미론**을 따른다.

**값**은 `불변 데이터 조각`으로 정의하고  
**값 타입**은 `불변 데이터 조각의 동작을 정의하는 타입`으로 정의하자

Integer는 값 타입, 7은 값이다.  

## 값을 선호해야만 하는 이유는 무엇인가?

값은 `불변 데이터`다.  

1. 맵의 키나 집합 원소로 불변 객체를 넣을 수 있다.
2. 불변 객체의 컬렉션에 대해 이터레이션하는 경우 내부 원소가 달라질지 염려할 필요가 없다.
3. 불변 객체를 쓰면 되돌리기나 다시하기 등도 쉽게 구현할 수 있다. ❓
4. 여러 스레드에서 불변 객체를 안전하게 공유할 수 있다.
