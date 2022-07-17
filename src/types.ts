import type {ReactNode} from 'react';
import type {StyleProp, ViewStyle} from 'react-native';

export interface BlurViewProperties {
  radius: number;
  style?: StyleProp<ViewStyle>;
  children?: ReactNode;
}
