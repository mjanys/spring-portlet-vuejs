<%--@elvariable id="items" type="java.util.List<cz.janys.iface.dto.PersonDto>"--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf" %>

<div id="${ns}urlState">
    <a href='{{urlData | queryString | hash}}'>Current url</a>

    <form action="" @submit.prevent="addUrlData($event)">
        <h3>Set</h3>
        <input v-model="urlDataToAdd.key"/>
        <input v-model="urlDataToAdd.value"/>
        <input type="submit" v-show="false"/>
    </form>

    <form action="" @submit.prevent="removeUrlData($event)">
        <h3>Remove</h3>
        <input v-model="urlDataToRemove.key"/>
        <input type="submit" v-show="false"/>
    </form>

    <div class="well">
        {{urlData | json}}
    </div>
</div>

<script type="text/javascript">
    new UrlState().$mount('#${ns}urlState');
</script>
