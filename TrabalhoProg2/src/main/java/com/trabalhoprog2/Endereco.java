package com.trabalhoprog2;

    public class Endereco {
        private String cep;
        private String rua;
        private String numero;
        private String cidade;
        private String estado;
        
        public Endereco(String cep, String rua, String numero, String cidade, String estado) {
            this.cep = cep;
            this.rua = rua;
            this.numero = numero;
            this.cidade = cidade;
            this.estado = estado;
        }
        public String getCep() {
            return cep;
        }
        public String getRua() {
            return rua;
        } 
        public String getNumero() {
            return numero;
        }
        public String getCidade() {
            return cidade;
        } 
        public String getEstado() {
            return estado;
        } 
        public void setNumero(String numero) {
            this.numero = numero;
        } 
        
        @Override
        public String toString() {
            return rua + ", " + numero + " - " + cidade + "/" + estado + " (CEP: " + cep + ")";
        }
    }
