package uz.akh.subscription

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.apache.commons.logging.LogFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.lang.Exception
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class FeignErrorDecoder : ErrorDecoder {

    val mapper = ObjectMapper()
    override fun decode(methodKey: String?, response: Response?): Exception {
        response?.apply {
            if (status() == 400) {
                val message = (mapper.readValue(this.body().asInputStream(), BaseMessage::class.java))
                return FeignErrorException(message.code, message.message)
            }
        }
        return GeneralApiException("Not handled")
    }
}

@Configuration
class WebMvcConfigure : WebMvcConfigurer {
    @Bean
    fun errorMessageSource() = ResourceBundleMessageSource().apply {
        setDefaultEncoding(Charsets.UTF_8.name())
        setDefaultLocale(Locale("uz"))
        setBasename("error")
    }

    @Bean
    fun localeResolver() = SessionLocaleResolver().apply { setDefaultLocale(Locale("ru")) }

    //Har bitta requestni servletga kelishi bilan headerni o'qib languageni contextga set qiladi
    @Bean
    fun localeChangeInterceptor() = HeaderLocaleChangeInterceptor("hl")

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())//Betda yozgan interceptorimizani register qp qoyvommiza
    }
}

class HeaderLocaleChangeInterceptor(val headerName: String) : HandlerInterceptor {
    private val logger = LogFactory.getLog(javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val newLocale = request.getHeader(headerName)
        if (newLocale != null) {
            try {
                LocaleContextHolder.setLocale(Locale(newLocale))
            } catch (ex: IllegalArgumentException) {
                logger.info("Ignoring invalid locale value [" + newLocale + "]: " + ex.message)
            }
        } else {
            LocaleContextHolder.setLocale(Locale("uz"))
        }
        return true
    }
}