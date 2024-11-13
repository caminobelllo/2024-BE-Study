### 프로토콜과 HTTP

- HTTP 요청으로 보내는 데이터는 Request Body에,
- HTTP 응답으로 보내는 데이터는 Response Body에 담긴다.

### 컨트롤러 계층

API 서버는 주로 json 데이터를 응답한다.

스프링에서는 `@ResponseBody`를 사용하면 메서드가 자바 객체를 반환했을 때 객체를 json 데이터로 변환해 response body에 담아서 응답한다.

컴포넌트 스캔을 위해 빈으로 등록할 때 `@Controller`를 사용하지만, 이 경우에는 보통 편의를 위해 `@RestController`를 사용한다. `RestController`는 내부적으로 @Controller 어노테이션과 @ResponseBody를 모두 포함하고 있다.

<hr />

#### 요청 생성

컨트롤러 계층은 서비스 계층에 의존하고 있으며, **생성자 주입** 방식을 통해 서비스 빈을 주입 받는다.

**`@RequestMapping`**을 이용해 메서드가 처리할 요청 method와 url을 지정한다.

```
// POST의 경우
// POST /todo로 요청을 받으면 createTodo() 메서드를 호출한다
@RequestMapping(method = RequestMethod.POST, value = "/todo")
public void createTodo() {}
```

보통 하나의 컨트롤러에서 특정 요청과 관련한 것을 모두 처리하기 때문에, 클래스에서 공통 URL을 지정해 줄 수도 있다.

```
// todo 관련 요청을 모두 처리하는 컨트롤러의 경우
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @RequestMapping(method = RequestMethod.POST)
    public void createTodo() {}

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public void getTodoList() {}
}
```

이때 url의 뒷부분이 없다면 (즉, 그냥 요청 url이 "/todo"라면) 뒷부분인 value를 생략할 수 있다.

또한, 매번 method를 지정할 수도 있으나 편의를 위해 어노테이션을 통해 지정하도록 한다.
`@RequestMapping(method = RequestMethod.POST)`은 **`@PostMapping`**
`@RequestMapping(method = RequestMethod.GET)`은 **`@GetMapping`**

`@RequestBody`를 사용해서 파라미터로 들어오는 json 데이터를 자바 객체로 변환해서 받을 수 있다.

이때 **데이터를 받는 자바 객체**를
**DTO (Data Transfer Object)**
라고 한다.

<br />
<hr />

#### 응답 생성

스프링이 제공하는 `ReponseEntity`를 통해 http 응답을 만들어서 반환한다.
