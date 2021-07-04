package com.pkglobal.model;

import java.util.Objects;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MessageResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2020-11-25T12:48:48.427Z")



public class MessageResponse {
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("data")
    private String data = null;

    public MessageResponse status(String status) {
        this.status = status;
        return this;
    }

    /**
     * status
     * 
     * @return status
     **/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MessageResponse data(String data) {
        this.data = data;
        return this;
    }

    /**
     * User's message
     * 
     * @return data
     **/

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageResponse messageResponse = (MessageResponse) o;
        return Objects.equals(this.status, messageResponse.status)
                && Objects.equals(this.data, messageResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MessageResponse {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

