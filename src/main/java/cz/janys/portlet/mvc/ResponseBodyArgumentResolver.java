package cz.janys.portlet.mvc;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Martin Janys
 */
public class ResponseBodyArgumentResolver implements ModelAndViewResolver {

    private final Gson gson = new Gson();

    private class GsonView implements View {

        private final Object value;

        public GsonView(Object value) {
            this.value = value;
        }

        @Override
        public String getContentType() {
            return new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")).toString();
        }

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.getWriter().write(gson.toJson(value));
        }
    }

    @Override
    public ModelAndView resolveModelAndView(Method method, Class clazz, final Object returnValue, ExtendedModelMap modelMap, NativeWebRequest request) {
        if (canHandle(method, clazz, returnValue, modelMap, request)) {
            return new ModelAndView() {
                @Override
                public View getView() {
                    return new GsonView(returnValue);
                }
            };
        }
        return UNRESOLVED;
    }

    private boolean canHandle(Method method, Class clazz, Object returnValue, ExtendedModelMap modelMap, NativeWebRequest request) {
        return method.getAnnotation(ResponseBody.class) != null && request.getNativeResponse() instanceof ResourceResponse;
    }
}
