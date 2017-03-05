<html>
<head>
<title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form method="post" action="servlet/UploadServlet" encType="multipart/form-data" >
    <font color="blue">可直接发布zip文件</font> <br />
          发布流程文件 :<input type="file" name="processDef" />
   <input type="submit"  value="部署"/>
 </form>
</body>
</html>