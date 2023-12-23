package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void test1() {
        String message = ms.getMessage("hello", null, null);
        assertThat(message).isEqualTo("안녕");
    }


    @Test
    void test2() {
        assertThatThrownBy(()-> ms.getMessage("nocode", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void test3() {
        String message = ms.getMessage("nocode",null, "기본 메시지", null);
        assertThat(message).isEqualTo("기본 메시지");
    }

    @Test
    void test4() {
        String message = ms.getMessage("hello.name",new Object[]{"Spring"}, "기본 메시지", null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void test5() {
        String message1 = ms.getMessage("hello.name",new Object[]{"Spring"}, "기본 메시지", Locale.KOREA);
        String message2 = ms.getMessage("hello.name",new Object[]{"Spring"}, "기본 메시지", Locale.ENGLISH);
        assertThat(message1).isEqualTo("안녕 Spring");
        assertThat(message2).isEqualTo("hello Spring");
    }
}
