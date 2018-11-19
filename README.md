##1、maven打包命令，到gym工程下执行以下命令，会在gym-web/target下面生成gym.war
### -pdev clean package -Dmaven.test.skip=true
##2、把war包拷到tomcat的webapps下面，删除tomcat自带的所有工程，进入tomcat/conf/server.xml，在Host标签下加入以下代码
###Context path="/" docBase="gym" reloadable="true" source="org.eclipse.jst.jee.server:gym"
