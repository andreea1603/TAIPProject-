package com.example.neurodiagnosis.storage;

public interface IRemoteStorage {
    void addFile(String name, byte[] content);
    String getFileUrl(String name);
    void deleteFile(String name);
}
