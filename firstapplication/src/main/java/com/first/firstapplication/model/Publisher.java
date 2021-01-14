package com.first.firstapplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Publisher {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String nip;
    private String address;
        public Publisher() {
        }

        public Publisher(String name, Long id, String nip, String address) {
            this.id=id;
            this.name = name;
            this.nip = nip;
            this.address = address;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Publisher{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", nip='" + nip + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }