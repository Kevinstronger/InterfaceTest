#coding=UTF-8
import httplib, json, re

header = ['title', 'statisticID', 'GUID', 'type', 'id', 'imgURL', 'adURL']
channelList = ['video', 'videoids']
video = ['videoLength', 'videoSizeHigh', 'videoURLLow', 
         'GUID', 'id', 'videoPublishTime', 'videoSizeLow', 
         'title', 'shareURL', 'videoSizeMid', 'GeneralTitle', 
         'videoURLMid', 'type', 'statisticID', 'lastPlayedTime', 
         'longTitle', 'audioURL', 'CP', 'desc', 'videoURLHigh', 
         'playTimes', 'collect', 'imgURL']

url = 'v.ifeng.com'
conn = httplib.HTTPConnection(url, 80)
conn.request('GET', '/appData/subChannelList/100409-0_androidPhone_1_test.js')
res = conn.getresponse()
if res.status == httplib.OK:
    data = res.read()
    result = json.loads(data)
    #level1
    print result.keys()
    #level2 header
    print len(result['header'])
    for ele in result['header']:
        l2 = ele.keys()
        if (sorted(header) == sorted(l2)):
            print "Matched!"
        else:
            print "The keywords don't match ones defined in document."
    
    #level2 channelList
    #print len(result['channelList'])
    for i in range(0, len(result['channelList'])):
        
        list3 = result['channelList'][i]
        #print list3
        l3 = list3. keys()
        
        l4 = list3['video'][0].keys()
        videoPublishTime = list3['video'][0]['videoPublishTime']
        #验证时间格式是否正确
        if not videoPublishTime == '':
            if re.search('\d{4}\-\d{2}\-\d{2} \d{2}:\d{2}:\d{2}$', videoPublishTime):
                print "The datatime format is correct!"
            else:
                print "The format is incorrect!"
        #print l4
        
        #print (sorted(l4) == sorted(video))
    
else:
    print 'StatusCode = ', res.status,'reason = ', res.reason 

