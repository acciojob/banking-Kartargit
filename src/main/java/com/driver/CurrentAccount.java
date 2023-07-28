package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isValid(tradeLicenseId)){
           String rearrangeLicence = reArrangeLicenseId(tradeLicenseId);
           if(rearrangeLicence == ""){
               throw new Exception("Valid License can not be generated");
           }
           else{
               this.tradeLicenseId = rearrangeLicence;
           }
        }
    }
    public boolean isValid(String tradeLicenseId){
        for(int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }
    public String reArrangeLicenseId(String licenseId){
        int freq[] = new int[26];
        int n = licenseId.length();
        for(int i=0;i<n;i++){
            freq[licenseId.charAt(i)-'A']++;
        }
        StringBuilder ans = new StringBuilder();
        int i = 0;
        char prev = '*';
        while(i<n){
            int max = 0,ind=0;
            for(int j=0;j<26;j++){
                if(freq[j]>max&&prev!=(j+'A')&&freq[j]>0){
                    max = freq[j];
                    ind  = j;
                }
            }
            if(max==0)return "";
            prev=(char)(ind+'A');
            ans.append(prev);
            freq[ind]--;
            i++;
        }
        return ans.toString();
    }
}
