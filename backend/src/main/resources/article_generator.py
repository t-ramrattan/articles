import loremipsum
import json
import requests

class Article:
    def __init__(self, id, author, title, caption, imgUrl, content):
        self._id = id
        self.author = author
        self.title = title
        self.caption = caption
        self.imgUrl = imgUrl
        self.content = content
names = requests.get('http://uinames.com/api/?amount=50&region=united%20states').json()
names = list(names)

images = requests.get('http://picsum.photos/v2/list?page=5&limit=50').json()
images = list(images)
img_urls = []
for img in images:
    img_urls.append(str.format('http://picsum.photos/id/{}/498/280', img['id']))

def createTitle(string):
    string = string.strip()
    return string.title()

print("[")

for i in range(0,50):
    author = str.format('{} {}', names[i]['name'], names[i]['surname'])
    title = createTitle(requests.get('https://loripsum.net/api/1/short/prude/plaintext').text.split('.')[1].split(',')[0])
    caption = requests.get('https://loripsum.net/api/1/medium/prude/plaintext').text
    imgUrl = img_urls[i]
    content = requests.get('https://loripsum.net/api/4/long/prude').text
    article = Article(i, author, title, caption, imgUrl, content)
    print(str.format('{},',json.dumps(article.__dict__)))

print("]")