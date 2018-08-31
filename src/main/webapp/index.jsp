<%@page contentType="text/html; ISO-8859-1" pageEncoding="utf-8" %>
<html>
<body>
<h2>Hello World!</h2>
商品图片上传
<form name="form1" action="/mmall/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="上传文件">
</form>
富文本图片上传
<form name="form1" action="/mmall/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="上传文件">
</form>
</body>
</html>
