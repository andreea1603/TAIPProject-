package com.example.neurodiagnosis.infrastructure.storage;

public interface IRemoteStorage {
    void addFile(String name, byte[] content);
    String getFileUrl(String name);
    void deleteFile(String name);
}
