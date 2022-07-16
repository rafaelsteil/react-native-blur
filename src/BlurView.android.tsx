import React from 'react';
import {requireNativeComponent} from 'react-native';

import {BlurViewProperties} from '.';

export const BlurView = ({radius, style, children}: BlurViewProperties) => {
  return (
    <NativeBlurView radius={radius} style={style}>
      {children}
    </NativeBlurView>
  );
};

const NativeBlurView = requireNativeComponent<BlurViewProperties>('BlurView');
