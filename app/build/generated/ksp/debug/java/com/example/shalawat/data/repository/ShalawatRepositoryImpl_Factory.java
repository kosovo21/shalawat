package com.example.shalawat.data.repository;

import com.example.shalawat.data.audio.AudioFileManager;
import com.example.shalawat.data.local.ShalawatDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ShalawatRepositoryImpl_Factory implements Factory<ShalawatRepositoryImpl> {
  private final Provider<ShalawatDao> daoProvider;

  private final Provider<AudioFileManager> audioFileManagerProvider;

  private ShalawatRepositoryImpl_Factory(Provider<ShalawatDao> daoProvider,
      Provider<AudioFileManager> audioFileManagerProvider) {
    this.daoProvider = daoProvider;
    this.audioFileManagerProvider = audioFileManagerProvider;
  }

  @Override
  public ShalawatRepositoryImpl get() {
    return newInstance(daoProvider.get(), audioFileManagerProvider.get());
  }

  public static ShalawatRepositoryImpl_Factory create(Provider<ShalawatDao> daoProvider,
      Provider<AudioFileManager> audioFileManagerProvider) {
    return new ShalawatRepositoryImpl_Factory(daoProvider, audioFileManagerProvider);
  }

  public static ShalawatRepositoryImpl newInstance(ShalawatDao dao,
      AudioFileManager audioFileManager) {
    return new ShalawatRepositoryImpl(dao, audioFileManager);
  }
}
