#coding:utf-8
import sys
import smtplib
from email.mime.text import MIMEText
from email.header import Header

parameter1_emailAddress = sys.argv[1]
parameter2_repositoryName = sys.argv[2]
parameter3_previewUrl = sys.argv[3]

# 以下邮件代发服务器账号仅供测试
# 此处请填写成自己的邮件代发服务器配置（基于SMTP协议）
mail_host="smtp.qq.com"
server_port=465
mail_user="1600063551@qq.com"
mail_pass="qoxajqvfwthdbaba"

sender = mail_user
receivers = [parameter1_emailAddress]

emailMsg = "<center><h2>项目构建成功</h2></center><center><p><a href=\"" + parameter3_previewUrl + "\" style=\"color:red\">点击预览项目</a></p></center>"
message = MIMEText(emailMsg, 'html', 'utf-8')
message['From'] = Header("CI-服务器 <" + mail_user + ">", 'utf-8')
message['To'] =  Header("<" + parameter1_emailAddress + ">", 'utf-8')
subject = "项目" + parameter2_repositoryName + "-任务构建通知"
message['Subject'] = Header(subject, 'utf-8')

try:
    smtpObj = smtplib.SMTP_SSL()
    smtpObj.connect(mail_host, server_port)
    smtpObj.login(mail_user,mail_pass)
    smtpObj.sendmail(sender, receivers, message.as_string())
    print "Send Success\n0"
except smtplib.SMTPException:
    print "Error: Send Failed\n401"
