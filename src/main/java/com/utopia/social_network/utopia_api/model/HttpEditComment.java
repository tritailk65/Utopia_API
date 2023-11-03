package com.utopia.social_network.utopia_api.model;

public class HttpEditComment{
        public long idUser;
        public String comment;

        public HttpEditComment(long idUser, String comment) {
            this.idUser = idUser;
            this.comment = comment;
        }

        public long getIdUser() {
            return idUser;
        }

        public void setIdUser(long idUser) {
            this.idUser = idUser;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
        
}
