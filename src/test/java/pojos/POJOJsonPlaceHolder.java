package pojos;

public class POJOJsonPlaceHolder {

    /*
    Request Body
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */

    // 1- Objemizin icerisinde var olan tum Key degerlerini private Variable olarak tanimlayalım:

    private String title;
    private String body;
    private int id;
    private int userId;

    // 2- Getter ve Setter hazirla


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
        // 3- Tum parametreleri iceren Constructor create et

        public POJOJsonPlaceHolder(String title, String body, int id, int userId) {
            this.title = title;
            this.body = body;
            this.id = id;
            this.userId = userId;
        }

        // 4- Parametresiz Constructor create edelim
        public POJOJsonPlaceHolder(){
        }

        // 5- toString() metodu create et

    @Override
    public String toString() {
        return "POJOJsonPlaceHolder{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", id=" + id +
                ", userId=" + userId +
                '}';
    }
}

