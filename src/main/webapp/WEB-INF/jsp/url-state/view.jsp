<%--@elvariable id="items" type="java.util.List<cz.janys.iface.dto.PersonDto>"--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>

<div id="${ns}urlState">
    <a href='{{urlData | queryString | hash}}'>Current url</a>
    <div class="well">
        {{urlData | json}}
    </div>
</div>

<script type="text/javascript">
    new UrlState().$mount('#${ns}urlState');
</script>
