#coding=UTF-8

import httplib, json

class CityCode(object):
    #将城市代码存入字典中
    valid_code = {
                  101010300: '朝阳',
                  101011000: '石景山',
                  101010900: '丰台'
                  }
    #将方法声明为类方法，这样就可以不创建实例对象，直接使用类名.方法名即可S
    @classmethod
    def is_city_code_valid(self, code):
        #.get(code), code为字典中的key
        if self.valid_code.get(code):
            return '/data/sk/%s.html' % code        
        else:
            print "Please provide valid code, and try it again."
            #exit(0)为正常退出，非0为非正常退出
            exit(-1)
        

#testcode = CityCode()
#print testcode.is_city_code_valid(10101100)
 
    

def weather_info(code):
    url = 'www.weather.com.cn'
    conn = httplib.HTTPConnection(url, 80)
    conn.request('GET', CityCode.is_city_code_valid(code))
    #print CityCode.is_city_code_valid(101010300)
    res = conn.getresponse()
    
    if res.status == httplib.OK:
        data = res.read()
        result = json.loads(data)['weatherinfo']
        #print json.dumps(result,ensure_ascii=False, encoding='UTF-8')
        print u'城市： ',result['city']
        print u'温度： ',result['temp'],"C"
        print u'风力： ',result['WS']
    conn.close()
        
if __name__ == '__main__':
    weather_info(101011000)
'''
当输入正确代码后的结果：
城市：  石景山
温度：  7 C
风力：  1级

输入错误代码后的结果：
Please provide valid code, and try it again.
'''
    

   
    

