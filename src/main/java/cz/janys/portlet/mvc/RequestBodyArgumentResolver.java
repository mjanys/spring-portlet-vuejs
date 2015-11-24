package cz.janys.portlet.mvc;

import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.portlet.ClientDataRequest;

/**
 * @author Martin Janys
 */
public class RequestBodyArgumentResolver implements WebArgumentResolver {

    private final Gson gson = new Gson();

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        if (canHandle(methodParameter, webRequest)) {
            ClientDataRequest clientDataRequest = webRequest.getNativeRequest(ClientDataRequest.class);

            return gson.fromJson(clientDataRequest.getReader(), methodParameter.getParameterType());
        }
        else {
            return UNRESOLVED;
        }
    }

    private boolean canHandle(MethodParameter methodParameter, NativeWebRequest webRequest) {
        return methodParameter.hasParameterAnnotation(RequestJson.class) && webRequest.getNativeRequest() instanceof ClientDataRequest  ;
    }

}
