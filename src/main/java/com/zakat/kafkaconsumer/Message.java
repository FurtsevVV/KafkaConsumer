package com.zakat.kafkaconsumer;

public class Message {

    private int id;
    private String text;
    private int quantity;

    public Message(int id, String text, int quantity) {
        this.id = id;
        this.text = text;
        this.quantity = quantity;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
