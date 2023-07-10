# URI & Web Browser Request Flow

## URI : Uniform Resource Identifier
- **U**niform : 리소스 식별하는 통일된 방식
- **R**esource : 자원, URI로 식별할 수 있는 모든 것
- **I**dentifier :  다른 항목과 구분하는 데 필요한 정보
![image](https://github.com/suzieep/TIL/assets/61377122/3e08f45c-4474-4294-8fab-3b24ed4659e9)
- URL : Uniform Resource Locator - 리소스가 있는 위치를 지정
- URN : Uniform Resource Name - 리소스에 이름을 부여(잘 안씀!)

## URL
- 프로토콜(https)
- 호스트명(www.google.com) : Domain name or IP Address
- 포트 번호(http:80, https:443) - 일반적으로 생략
- path (/search)
- 쿼리 파라미터(q=hello&hl=ko)

![image](https://github.com/suzieep/TIL/assets/61377122/1389be65-d561-44c0-be23-7788dc92b8c3)


## MIME : Multipurpose Internet Mail Extensions
- 파일 변환
    - Binary -> text : Encoding
    - Text -> binary : Decoding
- Content-type : MIME 실체가 인코딩하고 있는 데이터의 종류(data-type/sub-type)

### MIME type
- text : 특정 문자셋으로 구성된 텍스트정보 등 formatted text정보 전송에 사용
    - text/plain, text/html, text/css, text/javascript
- multipart : 모든종류의 이미지를 나타내며, 비디오는 포함하지 않는다 
    - multipart/mixed , multipart/alternative , multipart/form-data, multipart/byteranges 
- audio : 모든종류의 오디오 파일전송 
    - audio/midi, audio/mpeg, audio/webm, audio/ogg, audio/wav
- video : 모든종류의 비디오 파일 전송 
    - video/webm, video/ogg
- application : 모든 바이너리 데이터 전송 
    - application/octet-stream, application/pkcs12, application/vnd.mspowerpoint, application/xhtml+xml, application/xml,  application/pdf
 
```
- application/octet-stream : 바이너리 타입을 위한 기본값
- text/plain : 텍스트파일에 대한 기본값
- text/css : 웹페이지 내에서 보통 인터프리터 되어야하는 모든 css파일 
- text/html : 모든 html컨텐츠는 이타입과 함께 서브된다. 
- image/gif (무손실압축), image/jpeg, image/png, image/svg+xml (백터이미지) 
- multipart/form-data,multipart/byteranges
```

### MIME Header
- MIME-Version
    - 모든 MIME 메시지에는 MIME-Version 헤더가 반드시 포함
    - 현재는 MIME 1.0 Version만 존재
- Content-Type
    - MIME 실체가 인코딩하고 있는 데이터의 종류를 설명
- Content-Transfer-Encoding
    - 메시지 데이터를 인코딩 하는 방식
- Content-ID
    - MIME 컨텐트에 특별한 식별 코드를 할당할 수 있도록 함
