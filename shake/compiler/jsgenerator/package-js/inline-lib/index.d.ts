export type DeclarationFunction<T> = () => T
export interface PackageDescriptor {
    packages: PackageContentDescriptor
}
export interface PackageContentDescriptor {
    [key: string]: PackageContentDescriptor | DeclarationFunction<any>;
}
export interface PackageContainer {
    [key: string]: PackageContainer;
    $$it: any;
}

export interface PackageSystem {
    packages: PackageContainer;
    import(path: string): any;
    add(descriptor: PackageContentDescriptor): void;
}

export const packages: PackageSystem;
export const createPackageSystem: (PackageDescriptor) => PackageSystem;
export function require(path: string): () => DeclarationFunction<any>;
export function object<T>(any: T): DeclarationFunction<T>;