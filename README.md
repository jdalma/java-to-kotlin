
```
"리팩터링을 하고 싶다면, 필수 전제조건은 탄탄한 테스트가 있어야 한다." 
- 마틴 파울러
```

> 코틀린은 `가변성` + `OOP`를 바탕에 깔고 있는 언어이기 때문에 아에 가변성을 쓰지 않을 이유는 없다
> 
> 1. 전체적인 프로젝트의 흐름은 객체지향을 쓴다
> 2. 객체지향을 쓸 때도 꼭 필요한 경우가 아니라면 가변 필드 + 가변 객체를 쓰는 일은 지양한다
> 3. 객체 사이의 통신에 사용하는 메서드는 가능하면 명시적 수단 (파라미터와 반환 값을 사용)을 활용하고 부수효과(side effect)를 만들지 않는다
> 4. 한 함수 / 메서드 내부에는 가능한 불변성을 활용한다
> 5. 여러 원소를 저장할 컬렉션에 대한 집계, 필터링, 변환 등의 로직을 직접 구현하기 보다는 표준 컬렉션 라이브러리를 활용한다
>
> 상태를 경계에 잘 가둬서 처리하느냐 → **객체지향**    
> 상태 변화를 없애고 상태라는 걸 값을 가지고 애뮬레이션 하느냐 → **함수형**  
> 함수 경계 안에서는 불변객체와 컬렉션을 쓰고 각종 순수 함수를 도우미로 써서 함수형으로 처리하면 좀 더 코드를 쉽게 작성할 수 있고,    
> 전체적으로는 객체지향적으로 사고하면서 모듈간의 결합도를 최소화하고 응집도를 최대하한다는 기본 원칙을 지켜나가는 스타일이 이미 자바 개발자인 분들에게는 최선이 될 것이다.    
> - 자바에서 코틀린으로 옮긴이 [오현석](http://www.yes24.com/24/AuthorFile/Author/192002)


> 값 컨텍스트로 프로그래밍하면 그 여파가 미치는 곳은 코드의 디자인적인 측면이나 설계까지 전부 영향이가고,    
> 더 나아가 코드를 사용하는 방법 마저도 달라지고 필요한 자료구조도 달라진다.    
> 단지 저장에 해당되는 부분만 값으로 대치한다고 끝이 아니다. 값 컨텍스트를 도입하려면 값을 다루는 모든 프로그래밍 스킬도 배워야 한다.   
> 값과 함수형을 고려할 때는 조직 내 사람이라는 문제를 간과하지 않아야 한다.    
> 값 컨텍스트를 펼치고 함수형 프로그래밍을 팁에 도입한뒤 "넌 이거 왜 못해?"라고 하는 것은 폭력이 될 수 있다.
> - [맹기완](https://www.rocketpunch.com/@hikamaeng) 대표

## 무지 목록

- **값 의미론**
  - 객체 사이의 동등성이 객체의 정체성(=물리적 주소)에 의해 결정되지 않고 **객체 내의 필드 값에 의해 결정되는게 값 의미론**
- [`kotlinlang docs` 확장 프로그램](https://kotlinlang.org/docs/extensions.html)
- **동반 객체(Companion Object)**
- **확장 함수** 📌
- `@JvmStatic`, `@JvmOverloads` 📌
  - `@JvmStatic` 자바 클래스로 변환될 때 스태틱 메소드를 만들어준다. 
- `inline fun require(value: Boolean, lazyMessage: () -> Any): Unit`
- `Legs` 선언 시 `object` 키워드
- **함수 추출(Extract Function)**, **함수 타입**
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
- **클래스와 오브젝트 차이**
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

이번 5장에서는 클라이언트가 전달하는 `UserPreferences` 가변 객체에 대한 불변 참조를 **불변 객체에 대한 가변 참조로 바꾸는 것**이다.    
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
4. 여러 스레드에서 불변 객체를 **읽기 전용**으로 안전하게 공유할 수 있다.

***

# **6장. 자바에서 코틀린 컬렉션으로**

1. 자바는 코틀린에 전달한 컬렉션 내부를 변경할 수 있다는 사실을 인식하라
2. 자바가 코틀린으로부터 전달받은 컬렉션 내부를 변경할 수 있다는 사실을 인식하라
3. 자바 컬렉션을 사용하는 코드에서 컬랙션 내부 상태 변경을 제거하라
4. 상태 변경을 제거할 수 없는 경우에는 [방어적 복사](https://tecoble.techcourse.co.kr/post/2021-04-26-defensive-copy-vs-unmodifiable/)를 수행하라

## 노트

1. 메소드에서 암시적으로 컬렉션의 상태를 변경하지 말고 명시적으로 반환을 하자
   - `"어떤 데이터를 제자리에서 변경하는"코드 보단 "새로운 값을 계산하고 참조가 이 새 값을 가리키게 하는" 코드를 작성하자`

## 자바 컬렉션

> **에일리어싱 오류**를 디버깅하느라 인생 중 수백시간을 허비하고 난 뒤에야
> 
> 기본적으로 **불변 데이터**를 사용하는 게 더 낫다는 결론을 내리게 된다.
> 
> 자바 2 도입 이후에 변경 가능한 컬렉션 인터페이스를 사용하는 개발자의 경우에는 이런 상황에 처하게 된다.
> 
> `99p`

자바 10에는 컬렉션을 `AbstractImmutableList`로 복사해주는 `List.copyOf(collection)`이 생겼다.  
이렇게 만들어진 객체는 변경이 불가하고 원본과도 무관하다.  

하지만 이런 식으로 원본과 복사본을 고민해가면서 사용하는 것이 지겹고 실수하기도 쉽다.  

1. **공유된 컬렉션을 변경하지 말라**  
   - 서로 떨어진 두 코드 사이에 공유된 컬렉신여 있다면 이를 불변 컬렉션으로 취급하라  
2. **생성하되 변경하지는 말라**  
   - 참조를 결과로 반환하자마자 이를 불변 객체인 것처럼 취급해야 한다.  
3. **성능을 위해 컬렉션을 가변 컬렉션으로 공유해야 하는 경우에는 이름을 주의 깊게 붙이고 `accmulator`, 공유 범위를 최대한 제한하라**  

## 코틀린 컬렉션

코틀린 개발자는 자바 컬렉션 인터페이스에서 **상태를 바꾸는 메서드를 제거**하고, `kotlin.collections`패키지 안에서 `Collection<E>`, `List<E>`등의 인터페이스로 공개한다.  
그리고 이들을 `MutableCollection<E>`, `MutableList<E>` 등으로 **확장하면서 다시 상태 변경 메서드를 추가**했다.  

`java.util.List` ↔︎ `kotlin.collections.List` 서로 취급이 가능하다  
이 마법은 자바 `List`를 코틀린 `MutableList`로 취급할 수도 있게 해준다. (하지만 이런 짓을 하면 안된다.)  

**불변**, **일기 전용**, **가변**
- 가변이 아닌 코틀린 컬렉션에 대한 공식 용어는 불변이 아니고 **읽기 전용**이다.
- `UnmodifiableList` 인터페이스를 통해서 변경할 수는 없지만, 다른 방법으로 내용을 변경할 수는 있다.
- **진정한 불변 컬렉션**만 변경이 일어나지 않는다는 사실을 보장할 수 있다. `java.util.List.of(...)`
- 코틀린에서 일반적인 컬렉션 타입은 모두 불변 컬렉션 타입이고, 원소를 변화시킬 수 있는 경우에만 직접 가변 컬렉션으로 형변환을 하여 사용하는 방식을 채택했다
    - **소스 코드를 문서화하는 효과가 있다**

## 코틀린으로 변환하기 [예제 코드](https://github.com/jdalma/java-to-kotlin/commit/ca66a85a5f9c644e5bd44978c989e93a001515ae)

***

# **7장. 동작에서 계산으로**

**참조 투명성**  
프로그램에 어떤 함수가 있을 때 이 함수를 호출한 모든 부분에서 함수 호출을 그 함수 호출의 결괏값으로 치환해도 프로그램이 똑같이 작동할 때 이런 함수를 **참조 투명한 함수**라고 말한다.  

## 함수

이 책에서는 결과를 돌려주는 함수와 그렇지 않은 서브루틴, 객체와 연관이 되어 있든 함수 자체로 존재하든 관계없이 **함수**라는 용어로 가리킨다.  
- 함수가 특정 객체와 연관되어 있다는 점이 아주 중요한 경우에는 **메서드**라고 부르기도 한다.
코틀린의 `Unit`은 값이 없는 것이 아니라 **값이 없음을 표현하기 위해 항상 똑같은 싱글턴 값을 반환한다.**  

**실제로 함수를 나누는 더 유용한 방법은 `계산`과 `동작`으로 나누는 것이다.**  
코드가 동작인 동시에 계산일 수는 없고, 시간에 의존적인 동시에 독립적일 수는 없다.  
**어떤 계산 코드를 가져다 동작을 호출하게 만들면 해당 코드는 동작이 된다.**  

## 계산

함수가 계산이 되기 위해서는 **입력이 같을 때 항상 같은 출력을 반환해야만 한다.**  
- 따라서 같은 인자로 호출하면 항상 같은 결과를 돌려주는 함수가 계산이다.
- 확장 함수나 프로퍼티가 의존하는 데이터가 `값`인 경우에는 계산일 수 있다.

**데이터가 변하지 않는 경우에만 의존해야 한다.**  

## 동작

언제, 얼마나 많이 호출되느냐에 따라 결과가 달라지는 함수  
- 지역변수, 반환 값이 아닌 함수 외부에 영향을 끼친다는 점에서 **외부 효과**가 더 적절한 이름일 수 있다.
동작이 있으면 자유롭게 코드를 리팩토링 할 수 없다.  

**어떤 경우든 외부에서 관찰가능한 부수 효과가 있는 함수는 계산이 아니라 `동작`이다.**  
- `void`나 `Unit`을 반환하는 함수는 거의 항상 동작이다.
- 어떤 다른 변할 수 있는 데이터 소스로부터 데이터를 읽어서 결과를 돌려준다면 동작으로 분류할 수 있다.


## 추상 객체를 사용하여 `Immutable Collection`을 암시한다 ?


```java
public interface TripsJava {
    Set<TripJava> tripsFor(String customerId);
    Set<TripJava> currentTripsFor(String customerId, Instant at);
}
```

```kotlin
class InMemoryTrips : TripsJava {
    private val trips: MutableMap<String, MutableSet<TripJava>> = mutableMapOf()

    override fun tripsFor(customerId: String?) =
        trips.getOrDefault(customerId, emptySet<TripJava>())

    override fun currentTripsFor(customerId: String?, at: Instant?) =
        tripsFor(customerId)
            .filter { it.isPlannedToBeActiveAt(at) }
            .toSet()
}
```

TripsJava는 **불변 컬렉션**을 반환한다고 명시되어 있지만, 만약 `tripsFor()`에서 **가변 컬렉션**을 반환하면 어떻게 될까?  
**실제로 반환되는 하위 타입이 우선된다.**  

***

# **8장. 정적 메서드에서 최상위 함수로**

> 자바에서 독립 함수는 클래스의 메서드로 선언되어야 하지만, 코틀린에서는 최상위에 있는 존재로 선언이 될 수 있다.    
> 어떤 때 최상위 함수를 선호해야 하며, 자바에서 어떤 리팩터링을 통해 최상위 함수에 이를 수 있을까?

자바에서는 **클래스 수준의 인스턴스 캐시를 구현하기 위해 정적 필드를 사용하기도 했다.**  
정적 함수는 **그 함수가 작용할 대상 타입에 메서드를 추가하지 않고 기능을 추가하고 싶을 때 유용하다.**  
JVM은 실제로 진정한 최상위 함수가 아니라 정적 메서드만 지원한다.  

코틀린은 함수(그리고 프로퍼티와 상수)를 클래스 밖에 선언하도록 허용한다.  
JVM에서는 이런 함수를 둘 곳이 없으므로 **코틀린 컴파일러는 이런 최상위 선언을 넣기 위해 정적 멤버가 들어있는 클래스를 생성해준다.**  
컴파일러는 디폴트로 함수 정의가 들어있는 파일 이름으로 부터 클래스 이름을 파생시킨다.  
- `top-level.kt`안에 정의된 최상위 함수들은 `Top_LevelKt`라는 클래스 안의 정적 메서드가 된다.

`object 선언`은 싱글턴을 정의한다.  
`object`의 모든 멤버는 그 객체의 이름과 똑같은 이름의 클래스 안에 멤버로 들어가는데, `@JvmStatic`으로 지정하지 않는 경우 실제 정적 멤버로 컴파일 되지 않는다.  
- 코틀린은 싱글턴 객체가 다른 클래스를 확장하거나 인터페이스를 구현하도록 허용한다.  

**정적 멤버와 비정적 멤버를 한 클래스에 모아야 하는 경우**, 클래스 안에서 정적인 부분을 `companion object 동반객체`안에 위치시키면 된다.
동반 객체에 들어있는 선언은 해당 선언이 속한 파일 안에서 한 그룹으로 묶이며, 동반 객체의 멤버는 동반 객체가 속한 클래스 인스턴스의 비공개 상태에도 접근할 수 있다.  
- 동반객체도 다른 클래스를 확장하거나 인터페이스를 구현할 수 있다.

**따라서 코틀린에서는 인스턴스 영역의 함수가 아닌 함수들을 최상위 함수로 정의할 수도 있고, 싱글턴 객체의 멤버로 정의할 수 있으며, 이 싱글턴 객체는 동반 객체일 수도 있고 다른 타입에 속하지 않는 최상위 객체일 수도 있다.**  
이 세 가지가 모두 같다면 기본적으로 최상위 함수를 사용해야 할 것이다.    
- `MyType.of()`와 같은 팩토리 메소드는 동반 객체의 메서드로 함수를 정의해야 한다.

1. [`@file:JvmName("Shortlists")`](https://github.com/jdalma/java-to-kotlin/commit/09af98a03d8dfa2e390ef51286dd60aff983f4c9#diff-1044d609b913915ee4faa6e813de12d2a57582f596a0c236f2cce3d5eb03c23c)
   - **`import travelator.Shortlists.{method}` 이 형식으로 코틀린에서 코틀린으로 정의된 최상위 함수를 임포트할 수 없다.**
   - 코틀린의 입장에서 이 함수들은 패키지 안의 클래스 안에 정의되어 있지 않기 때문이다.
   - 코틀린 코드 컴파일 시점에 JVM에 `Shortlists`라는 클래스 안에 있는 정적 함수들을 알 수 없기 때문이다
   - 코틀린에서는 **`import travelator.{method}`** 이 형식으로 import 해야한다.

2. [`@JvmStatic`](https://github.com/jdalma/java-to-kotlin/commit/af61681c5e7b5847d16d653c3ac2ad48cce142f4)

3. [확장 함수로 변경](https://github.com/jdalma/java-to-kotlin/commit/e37139c4a733d79b61a250d80193d42d3a3b81e0#diff-1ffa997ca85179e20133c106e675d8ac5089c6e412ed5e5e480237295dc8e830)

***

# **9장. 문에서 식으로 (다중식 함수에서 단일식 함수로)**

> 단일식 함수를 언제, 왜 사용하는지 알아보고 어떤 코틀린 기능이 사용될까?  
> **단일식 함수를 계산에만 사용하라**  

식은 **선언적**이다.  
즉, 함수가 계산하는 값이 **무엇인지**선언하며 코틀린 컴파일러나 런타임 시스템이 그 게산을 수행하는 **방법**을 결정하도록 한다.  
코틀린에서는 대입이 문이지 식이 아니다.

1. [**Take 1 : 인라이닝**](https://github.com/jdalma/java-to-kotlin/commit/8cdfec4df37aaafccefe62451148298aacc8dc25)
   - `atIndex` 대입문을 인라이닝화
2. [**Take 2 : 새 함수 도입하기**](https://github.com/jdalma/java-to-kotlin/commit/64aa6db38a45ff2cbbf790bac36bcb4b9b289a6f) 
   - `emailAddress()` 함수로 추출
   - `parse()`는 단일식이 되었다.
   - `require`을 인라이닝하여 `when`을 사용해보자
3. [**Take 3 : `let`**](https://github.com/jdalma/java-to-kotlin/commit/5a6dc4a75c08bb685c5e9ddd5b4f573fa9f8802f)
   - Take 2에서 함수를 추출했던 이유는 `atIndex`값을 따로 지역 변수에 저장하지 않아도 블록 영역안에서 해당 값을 참조하고 싶어서였다.
   - 여기서 필요한 값이 하나뿐이기 때문에 `let`블록을 사용하면 함수를 정의하지 않아도 이런 영역을 만들 수 있다.
   - ```kotlin
     // [1] 이렇게 하면 let블록안에서 atIndex 지역변수를 가리킨다
     val atIndex = value.lastIndexOf('@')
     atIndex.let{
         // ...
     }
    
     // [2] 이렇게 람다 파라미터로 같은 이름을 지정하면 지역변수 대신 람다 파라미터 값을 쓰게 된다 
     atIndex.let{ atIndex -> 
         // ...
     }
    
     // [3] atIndex 지역 변수 인라이닝
     value.lastIndexOf('@').let { atIndex ->
         // ...
     }
     ```

4. [**Take 4 : 확장 함수 추가**](https://github.com/jdalma/java-to-kotlin/commit/52e42b38f2a5dac7376c3e604c035947da3aab4a)
   - 다시 처음으로 돌아가서 `atIndex`에 집중하지말고 `substring` 부분에 집중해보자
   - `split()` 함수를 최상위 함수로 추가


> **`parse()`가 무엇을 반환해야 할까?**  
> `parse()`에서 오류가 발생했을 때 예외를 던지지 않으면 리팩토링이 훨씬 더 쉬워진다는 점을 알아두자  
> `throw`는 `Nothing`을 반환하는 식이다. (Nothing은 함수나 식 계산이 값으로 끝나지 않는다는 사실을 드러낸다)    
> `EmailAddress?`를 반환하게 해서 실패시 null을 돌려주게 해도 될 것이다.  
> 하지만 이런 방식은 자바 클라이언트와 잘 들어맞지 않는다.  
> **자바에서는 타입 시스템이 널 가능성에 대해 경고해 주지 못하기 때문이다.**  
> 자바 클라이언트가 사라지면 예외를 던지는 부분을 제거해도 될 것이다.

함수를 단일식으로 표현하려고 노력하는 것은 잘 구분된 깔끔한 코드를 이끌어 주는 좋은 규범이 될 수 있다.  
**단일식 형태를 얻기 위해 보통 하위식을 별도의 함수로 분리한다.**  
함수 결과를 기술하는 식은 `컴퓨터가 결과를 얻기 위해 수행해야 하는 동작보다는 함수 파라미터를 기반으로 기술된다.`  

***

# [**10장. 함수에서 확장 함수로**](https://github.com/jdalma/java-to-kotlin/commit/71f665689fe08ee9228ff53ac24216eb33855bb3)

고전적인 객체 지향 언어에서는 `클래스에 메서드를 정의해서 객체가 메시지에 반응하는 방법을 정의`한다.  
함수형 프로그래밍에서는 `값을 사용해 함수를 호출함으로써 문제를 해결`한다.  

클래스를 **가능한 한 작게 유지하고 핵심이 아닌 연산을 외부 함수로 만드는 게 낫다.**  
확장 함수가 자신이 확장하는 클래스의 비공개 멤버에 대한 접근을 할 수 없다.  

확장 함수를 디컴파일 해보면 자바의 정적 메소드로 생성되어 **수신 객체는 파라미터로 받게 된다.**  
그래서 `this`가 `null`일 가능성이 있다.  

***

# [**11장. 메서드에서 프로퍼티로**](https://github.com/jdalma/java-to-kotlin/commit/fe2c9f8856275fc8745589aaf35820178ceab634)

**언제 계산된 프로퍼티를 선택해야 할까?**  
같은 타입에 속한 다른 프로퍼티에만 의존하고 계산 비용이 싼 경우에는 **계산된 프로퍼티를 택하라**는 것이다.  
계산 비용이 비쌀 경우에는 `lazy`키워드를 사용하는 방법도 있다.  

**자바에서의 프로퍼티 접근과 코틀린에서의 접근은 어떻게 다른가?**  
코틀린은 클래스의 필드 접근에 대한 결정을 개발자에게 맡기지 않고 `접근자 메서드만을 사용하여 접근하기로 결정`했다.  
- 정말 `public`필드가 필요하다면 `@JvmField`를 쓸 수 있다.


개인적인 생각으로는 `예제 11.2` 부터의 흐름은 `static Set<CampSite> sitesInRegion()`에 대한 메서드를 **확장 함수로 넓혀가는 작업이다**  
`예제 11.9`는 `Iterable<CampSite>`로 `CampSite`로 한정적이기 때문에 꽤 합리적인것으로 보인다.  

***

# **12장. 함수에서 연산자로**

> 시스템을 코틀린으로 점진적으로 변환해 나가는 과정에서 두 언어의 관습을 동시에 지원하려면 어떻게 해야 할까?

1. [`Money`클래스에 **산술 연산자 확장 함수**를 추가하는 방법](https://github.com/jdalma/java-to-kotlin/commit/23c2d5a197cb63ba3c0a2d809a1a4c075f3b3746)
   - `@JvmName`을 사용하면 코틀린과 자바에서 메서드가 다른 이름으로 불리게 할 수 있다.
2. [기존 코틀린 코드에서 정의한 연산자 호출하기](https://github.com/jdalma/java-to-kotlin/commit/461221605b65252e1db6c9fcf6c4e1f4f29a39ee)
   - 자바를 위해선 `add()`를 사용하고 코틀린에서는 `+` 연산자를 사용할 수 있게 `operator plus()`를 정의해놓는다.
3. [동반 객체 `operator fun invoke()`와 팩토리 메소드 `of()`를 통한 클래스 생성](https://github.com/jdalma/java-to-kotlin/commit/dc28a63b342303cc75037ce27d0ff19037e5f021)

> **팩토리 메소드에는 `두 가지 책임`이 있다.**  
> 1. 클래스를 생성하면서 클래스의 불변 조건을 강제하는 것
> 2. 값을 표현하는 객체를 생성하는 현대 자바 관습을 따르는 **설탕 문법**을 제공한다.

***

# **13장. 스트림에서 이터러블이나 시퀀스로**

> 코틀린이 자바 스트림 대신 무엇을 사용할까?  
> 어떻게 하면 자바 스트림을 이런 코틀린 구조로 변환할 수 있고, 언제 이런 변환을 수행해야 할까?

자바 스트림이 일반적인 컬렉션 변환, 지연 계산, 병렬 처리 등의 작업을 모두 염두에 두고 설계됐는데 이런 **작업의 요구조건이 모두 다르다는 점에 있다.**  
코틀린을 병렬 연산을 구현하려 시도하지 않고 **두 가지 추상화만을 제공한다.**  

첫 번째는 컬렉션 변환과 축약에 유용한 **이터러블**이고, 두 번째는 지연 계산을 제공하는 **시퀀스**다.  
코틀린의 시퀀스는 자바 스트림의 대체물이 아니다.  

`Stream.fillter`와 달리 코틀린 `Iterable.filter`는 `List`를 반환하기 때문에 함수 호출으 계속 연쇄할 수 있다.  

```kotlin
fun averageNonBlankLength(strings: List<String>): Double = 
    (strings 
        .asSequence() 
        .filter { it.isNotBlank() } 
        .map(String::length) 
        .sum() / strings.size.toDouble())
```

> 다만 모든 데이터를 읽기 전에는 작업하는 흉내조차 낼 수 없어서 **터미널 연산이 되어야 하는 `sum`함수**는 제외된다 (245p)      
- `sum` 함수는 `Sequence`를 반환하지 않는다는 뜻이다
  
> `Iterable<T>`의 확장 함수와 `Sequence<T>`의 확장 함수는 같은 파라미터를 받지만, 의미가 아주 다르기 때문에 서로 같은 유형의 연산이 아니다.  
> `Iterable`에 대한 연산은 **즉시 계산**이지만, `Sequnece`에 대한 연산은 **지연 계산**이기 때문에 이 둘을 아무 불이익 없이 바꿔 사용할 수는 없다  
> 반면 이들이 비슷한 API를 제공한다는 점은 이터러블과 시퀀스를 서로 전환하고 싶을 때 코드를 거의 바꾸지 않아도 된다는 사실을 의미한다.  

***

# **14장. 누적시키는 객체에서 변환으로**

> 가변 객체와 부수 효과에 의존하는 자바 코드를 불변 값을 변환하는 코틀린 코드로 바꾸는 가장 좋은 방법이 무엇일까?  


`CostSummary`와 `CostSummaryCalculator` 위주로 계산을 **가변 클래스에서 불변 데이터에 대해 작용하는 함수로 리팩토링해 보자**  
`CostSummary`를 불변 값 타입으로 만드는 것 부터 시작해서, `CostSummaryCalculator`까지 적용할 것이다.

1. [기준](https://github.com/jdalma/java-to-kotlin/commit/41b452d66ce7ed8fa89ee3d8378116cbccd38f80)
2. [제자리 정렬 수정, `apply` 적용해서 클라이언트가 객체 내부 필드를 추가하였다](https://github.com/jdalma/java-to-kotlin/commit/b824bb8e1fd0a366c9d4499d1a98e9c642db3e3c)
3. [부생성자 추가 및 `apply` 제거로 인해 객체 생성에 대한 책임은 스스로가 가지게 했다]()
4. [`fold`를 이용해 모두 불변 필드로 만들었다.](https://github.com/jdalma/java-to-kotlin/commit/95e1ac530589308d1400c868b5bb86985e3538d7)
5. [`data class`로 변경](https://github.com/jdalma/java-to-kotlin/commit/f9845e1a93628fc15c97df18535543080f6d3bf1)
   - 이 예제에서 `CostSummary`의 내부 필드를 계산기에서 다 계산하고 값을 전달해준다.
   - `CostSummary` 생성자에서 필요한 값을 받아 `CostSummary`가 직접 계산을 하여 스스로를 생성하여야 한다.

***

# **15장. 캡슐화한 컬렉션에서 타입 별명으로**

> 어떻게 해야 현재 설계에서 더 나은 설계에 도달할 수 있을까?

컬렉션을 도메인 타입으로 사용하는 것이 코틀린에서는 옳지만은 않다.  
**불변 컬렉션을 캡슐화하는 것이 규칙이 아니라 `예외`가 되어야만 한다.**  

새로운 기능을 추가하고 싶을 때 핵심 도메인 클래스가 모든 연산을 가져 코드 기반의 여러 부분이 결합되어 힘들 수 있다.  
- 클라이언트는 `Route`대신 `List<Journey>`에 대해 정의된 확장 함수를 통해 제공되는 연산을 사용하게 만들어야 한다.
- 실제로 컬렉션을 감싸는 클래스(`Route`처럼)는 불필요한 클래스가 아니라 나쁜 클래스다.  
  - 감싼 클래스를 벗겨낸 후 연산을 수행하고 다시 감싸야하는 불편함도 있다.
- 감싸는 클래스로 인해 처리할 때 **코틀린 표준 라이브러리가 제공하는 기능을 사용할 수 없기 때문이다.**
- `Route`가 `List<Journey>`를 **가지기를**원하지 않고, `List<Journey>` **이기를** 원한다. 

> "한 가지 데이터 구조에 사용할 수 있는 함수 100개가 열 가지 데이터 구조에 사용할 수 있는 함수 10개 보다 더 낫다."
> - [앨런 펄리스의 프로그래밍 격언](https://www.cs.yale.edu/homes/perlis-alan/quotes.html)

**타입 별명**을 사용하면 여러 문제들을 해결할 수 있다. (클라이언트 코드가 코틀린이어야 한다.)

***

# **16장. 인터페이스에서 함수로**

> 인터페이스는 계약의 두 당사자를 서로 결합시키기 때문에 소프트웨어가 더 유지보수하기 어려워진다.  
> 함수 타입이 이 문제를 어떻게 해결할 수 있을까?
    
클라이언트 코드가 구현 클래스만 의존하는 것 보다 인터페이스에 의존하게 하면 **각 구현체들의 설정과 구현 내용을 감출 수 있다.**  
**정보 은닉을 추구했지만 구현 은닉에 머문다.**  

**인터페이스를 익명으로 구현만 제공**한다면 클라이언트는 다운캐스팅을 하거나 다른 메소드를 호출하는 등의 조작이 불가능해진다.  
이 객체를 **클로저**라고 부른다.  
- 이 객체는 나중에 참조하기 위해 주변 문맥(함수 호출에서 전달받은 파라미터들)에서 자신에게 필요한 값들을 모두 감싸서 유지하기 때문이다.

```kotlin
fun interface Itest {
    fun printTest(message: String)
}

fun test(
    message1 : String,
    message2 : String
) = Itest {
        message ->(
        print(message + message1 + message2)
    )
}
```

위에서도 **람다가 자신을 둘러싼 함수의 파라미터 값을 포획하는 `클로저`이다.**  

> 객체지향 프로그래머가 어떤 기능의 성가신 세부 사항을 캡슐화해서 클라이언트가 그 기능을 사용할 때 마다  
> 세부 사항을 전달하지 않아도 되게 하는 문제를 어떻게 해결하는지 살펴봤다.  
> **함수형 프로그래머는 같은 문제를 어떻게 접근할까?**

함수형 용어에 **[부분 적용](https://en.wikipedia.org/wiki/Partial_application)** 이라는 기법이 있다.  
- **함수의 인자 중 일부를 고정시키면서 인자가 더 적은 새 함수를 만들어 내는 기법**
- 코틀린에서 **부분 적용**을 사용하는 가장 쉬운 접근 방법은 설정 정보를 일부만 적용하는 함수를 작성하는 것이다.  

## 15,16장 모임

1. "정적함 함수의 발견 가능성이 낮다는 점" → Util성 정적 메소드들을 찾을 수 있다는 것은 `Arrays`를 생각한다는 자체가 지식으로 알 수 있는 것 처럼 찾기가 힘들 수 있다는 것
2. `data class`는 생성자로 받은 필드들을 `public`하게 사용하는 것이 일반적이다.
3. `typealias`와 확장함수에 회의적
   - `typealias`가 적용된 타입에만 확장함수가 적용되면 좋은데,
   - `typealias A = List<B>`로 사용하게 되면 `A`도 `List`의 모든 확장함수를 사용할 수 있다.
4. `List<Journey>`가 무슨 의미인지 알 수 없을만큼 사이즈가 커진다면,
5. "인터페이스는 계약의 두 당사자를 서로 결합시키기 때문에 소프트웨어가 더 유지보수하기 어려워진다." 317p
   - **인터페이스로 결합하던, 시그니처로 결합하던 어떤 방법이 유지보수에 좋다고 단정짓기는 힘들다.**
   - 어떤 코드든 수명이 있기 마련이기 때문이다.
6. 익명 클래스의 인스턴스는 언제 사용하나??
   - `compare`
7. [자유 변수](https://ko.wikipedia.org/wiki/%EC%9E%90%EC%9C%A0_%EB%B3%80%EC%88%98%EC%99%80_%EC%A2%85%EC%86%8D_%EB%B3%80%EC%88%98)와 클로저의 정의
   - 클로저의 정의는 3가지로 나뉜다.
   - **스코프(scope)에 묶인 변수를 바인딩 하기 위한 일종의 기술**은 `현상`으로 클로저를 정의한 것이다
