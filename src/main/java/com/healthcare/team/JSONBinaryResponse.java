package com.healthcare.team;

public class JSONBinaryResponse {
        private String resourceType;
        private String contentType;
        private String id;
        private String presignedPutUrl;


        // Getter Methods

        public String getResourceType() {
            return resourceType;
        }

        public String getContentType() {
            return contentType;
        }


        public String getId() {
            return id;
        }

        public String getPresignedPutUrl() {
            return presignedPutUrl;
        }

        // Setter Methods

        public void setResourceType( String resourceType ) {
            this.resourceType = resourceType;
        }

        public void setContentType( String contentType ) {
            this.contentType = contentType;
        }

        public void setId( String id ) {
            this.id = id;
        }

        public void setPresignedPutUrl( String presignedPutUrl ) {
            this.presignedPutUrl = presignedPutUrl;
        }
    }

